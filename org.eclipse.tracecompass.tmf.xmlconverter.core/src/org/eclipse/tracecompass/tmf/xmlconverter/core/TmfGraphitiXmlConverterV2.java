package org.eclipse.tracecompass.tmf.xmlconverter.core;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.tracecompass.tmf.xmlconverter.core.model.Condition;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.ConditionSingle;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.DefinedValue;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.EventField;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.EventHandler;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.HeadProvider;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.ObjectFactory;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.StateAttribute;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.StateChange;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.StateProvider;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.StateValue;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.Tmfxml;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.HeadProvider.Label;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.HeadProvider.TraceType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TmfGraphitiXmlConverterV2 implements ITmfXmlConverter {
	
	private Document fGraphitiXml = null;
	public static final String FILE_NAME = "convert_xml.xml";
	
	private String fStatemachineTag = "statemachine:Statemachine";
	private String fStateAttributeTag = "stateAttribute";
	private String fStateValueTag = "stateValue";
	private String fConditonalStateTag = "condition";
	private String fTransitionTag = "transitions";
	
	private String fStateType = "statemachine:State";
	private String fConditionalStateType = "statemachine:ConditionalState";
	
	private String fAttributeConditionType = "statemachine:AttributeCondition";
	private String fEventFieldConditionType = "statemachine:FieldCondition";
	
//	private Map<Integer, Node> fStatesList = new HashMap<Integer, Node>();
//	private Map<Integer, Node> fTransitionsList = new HashMap<Integer, Node>();
	
	private Map<Integer, Node> fStatemachineList = new HashMap<>();
	//private Map<Integer, Node> fStateList = new HashMap<>();
	private Map<Integer, Map<Integer, Node>> fStatemachineStatesList = new HashMap<>();
	
	private Map<String, EventHandler> fEventHandlerList = new HashMap<>();
	
	private String traceType = "org.eclipse.tracecompass.tmf.core.development";
	private String xmlID;
	
	private int statemachinePositionInXml = 1;
	private int statePositionInStatemachine = 0;
	
	private ObjectFactory factory = new ObjectFactory();

	@Override
	public boolean convertXml(File xml) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			fGraphitiXml = dBuilder.parse(xml);
			fGraphitiXml.getDocumentElement().normalize();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			return false;
		}
		
		xmlID = xml.getName().replaceFirst("[.][^.]+$", "").replaceAll("[^a-z0-9A-Z]", ".");
		
//		NodeList nodeList = fGraphitiXml.getDocumentElement().getChildNodes();
//		for (int i = 0; i < nodeList.getLength(); i++) {
//			Node node = nodeList.item(i);
//			String nodeName = node.getNodeName();
//			if(nodeName.equals(fStateTag) || nodeName.equals(fInitialStateTag) || nodeName.equals(fFinalStateTag)) {
//				fStatesList.put(positionInXML, node);
//				++positionInXML;
//			} else if (nodeName.equals(fTransitionsTag)) {
//				fTransitionsList.put(positionInXML, node);
//				++positionInXML;
//			}
//		}
		NodeList statemachineNodeList = fGraphitiXml.getElementsByTagName(fStatemachineTag);
		for (int i = 0; i < statemachineNodeList.getLength(); i++) {	
			Node statemachineNode = statemachineNodeList.item(i);
			if(statemachineNode.getNodeType() == Node.TEXT_NODE) {
				continue;
			}
			fStatemachineList.put(statemachinePositionInXml, statemachineNode);
			++statemachinePositionInXml;
		}
		
		for(Entry<Integer, Node> statemachineNode : fStatemachineList.entrySet()) {
			Map<Integer, Node> states = extractStateInformation(statemachineNode.getValue());
			fStatemachineStatesList.put(statemachineNode.getKey(), states);
			statePositionInStatemachine = 0;
		}
		
		return buildXmlFile();
	}
	
	private Map<Integer, Node> extractStateInformation(Node parentStatemachine) {		
		NodeList stateNodeList = parentStatemachine.getChildNodes();
		Map<Integer, Node> statesMap = new HashMap<>();
		for (int i = 0; i < stateNodeList.getLength(); i++) {
			Node stateNode = stateNodeList.item(i);
			if(stateNode.getNodeType() == Node.TEXT_NODE) {
				continue;
			}
			statesMap.put(statePositionInStatemachine, stateNode);
			++statePositionInStatemachine;
		}
		return statesMap;
	}
	
	private Vector<Node> extractTransitionsNode(Node parentState) {
		NodeList transitionNodeList = parentState.getChildNodes();
		Vector<Node> transitionList = new Vector<>();
		for (int i = 0; i < transitionNodeList.getLength(); i++) {
			Node transitionNode = transitionNodeList.item(i);
			if(transitionNode.getNodeType() == Node.TEXT_NODE) {
				continue;
			}
			transitionList.add(transitionNode);
		}
		return transitionList;
	}
	
	private Vector<StateChange> extractStateChange(Node node) {
		Vector<StateChange> stateChanges = new Vector<>();
		NodeList stateChangeNodeList = node.getChildNodes();
		if(stateChangeNodeList.getLength() > 0) {
			for (int i = 0; i < stateChangeNodeList.getLength(); i++) {
				Node stateChangeNode = stateChangeNodeList.item(i);
				if(stateChangeNode.getNodeType() == Node.TEXT_NODE) {
					continue;
				}
				
				NodeList stateChangeInformationList = stateChangeNode.getChildNodes();
				Vector<StateAttribute> stateAttributeList = new Vector<>();
				StateValue stateValue = factory.createStateValue();
				extractAttribute(stateChangeInformationList, stateAttributeList, stateValue);
				StateChange stateChange = factory.createStateChange();
				stateChange.getStateAttribute().addAll(stateAttributeList);
				stateChange.setStateValue(stateValue);
				stateChanges.add(stateChange);
			}
		}
		return stateChanges;
	}
	
	private void extractAttribute(NodeList attributeNodeList, Vector<StateAttribute> stateAttributeList, StateValue stateValue) {
		for(int i = 0; i < attributeNodeList.getLength(); i++) {
			Node node = attributeNodeList.item(i);
			if(node.getNodeName().equals(fStateAttributeTag)) {
				StateAttribute stateAttribute = factory.createStateAttribute();
				String stateAttributeType = node.getAttributes().getNamedItem("type").getNodeValue();
				if(stateAttributeType.equals("query")) {
					stateAttribute = stateAttributeQuery(node);
				} else {
					stateAttribute.setType(stateAttributeType);
					stateAttribute.setValue(node.getAttributes().getNamedItem("value").getNodeValue());
				}
				stateAttributeList.add(stateAttribute);
			} else if(node.getNodeName().equals(fStateValueTag)) {
				String stateValueType = node.getAttributes().getNamedItem("type").getNodeValue();
				if(stateValueType.equals("definedState")) {
					stateValueType = "int";
				}
				stateValue.setType(stateValueType);
				stateValue.setValue(node.getAttributes().getNamedItem("value").getNodeValue());
			}
		}
	}
	
	private StateAttribute stateAttributeQuery(Node stateAttributeNode) {
		StateAttribute stateAttribute = factory.createStateAttribute();
		stateAttribute.setType(stateAttributeNode.getAttributes().getNamedItem("type").getNodeValue());
		stateAttribute.setValue(stateAttributeNode.getAttributes().getNamedItem("value").getNodeValue());
		
		NodeList queryStateAttributeList = stateAttributeNode.getChildNodes();
		for (int i = 0; i < queryStateAttributeList.getLength(); i++) {
			Node queryStateAttributeNode = queryStateAttributeList.item(i);
			if(queryStateAttributeNode.getNodeType() == Node.TEXT_NODE) {
				continue;
			}
			
			StateAttribute queryStateAttribute = factory.createStateAttribute();
			queryStateAttribute.setType(queryStateAttributeNode.getAttributes().getNamedItem("type").getNodeValue());
			queryStateAttribute.setValue(queryStateAttributeNode.getAttributes().getNamedItem("value").getNodeValue());
			stateAttribute.getStateAttribute().add(queryStateAttribute);
		}
		return stateAttribute;
	}
	
	private StateChange extractStateChangeFromCondition(Node condition) {
		// Hack to avoid text node
		Vector<Node> transitions = new Vector<>();
		Vector<Node> conditions = new Vector<>();
		NodeList nodeList = condition.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if(node.getNodeName().equals(fTransitionTag)) {
				transitions.add(node);
			} else if(node.getNodeName().equals(fConditonalStateTag)) {
				conditions.add(node);
			}
		}
		
		StateChange thenStateChange = null;
		StateChange elseStateChange = null;
		for(Node transition : transitions) {
			Vector<StateChange> transitionStateChange = extractStateChange(transition);
			if(!transitionStateChange.isEmpty()) {
				if(transition.getAttributes().getNamedItem("name").getNodeValue().equals("") || transition.getAttributes().getNamedItem("name").getNodeValue().equals("then")) {
					thenStateChange = transitionStateChange.firstElement();
				} else {
					elseStateChange = transitionStateChange.firstElement();
				}
			} else {
				// TODO IF - THEN - IF
			}
		}
		
		Vector<Condition> ifCondition = new Vector<>();
		for(Node conditionNode : conditions) {
			if(conditionNode.getAttributes().getNamedItem("xsi:type").getNodeValue().equals(fAttributeConditionType)) {
				StateChange conditionStateChange = factory.createStateChange();
				conditionStateChange = extractStateChange(conditionNode).firstElement();
				Condition attributeCondition = new Condition();
				attributeCondition.getStateAttribute().addAll(conditionStateChange.getStateAttribute());
				attributeCondition.setStateValue(conditionStateChange.getStateValue());
				ifCondition.add(attributeCondition);
			} else {
				EventField eventField = new EventField();
				String fieldName = conditionNode.getAttributes().getNamedItem("fieldName").getNodeValue();
				eventField.setName(fieldName);				
				
				Node stateValueNode = conditionNode.getChildNodes().item(1);
				String type = stateValueNode.getAttributes().getNamedItem("type").getNodeValue();
				String value = stateValueNode.getAttributes().getNamedItem("value").getNodeValue();
				StateValue stateValue = factory.createStateValue();
				stateValue.setType(type);
				stateValue.setValue(value);
				
				Condition fieldCondition = new Condition();
				fieldCondition.setField(eventField);
				fieldCondition.setStateValue(stateValue);
				ifCondition.add(fieldCondition);
			}
		}
		
		StateChange stateChange = factory.createStateChange();
		if(ifCondition.size() == 1) {
			ConditionSingle conditionSingle = new ConditionSingle();
			conditionSingle.setCondition(ifCondition.firstElement());
			stateChange.setIf(conditionSingle);
		} else {
			// TODO: Condition multiple
		}
		
		stateChange.setThen(thenStateChange);
		stateChange.setElse(elseStateChange);
		
		return stateChange;
	}
	private void extractCondition(Node targetState, EventHandler eventHandler) {
		NodeList conditionInformationList = targetState.getChildNodes();
		StateChange stateChange = factory.createStateChange();
		StateChange thenStateChange = factory.createStateChange();
		StateChange elseStateChange = factory.createStateChange();
		//String andExpression = targetState.getAttributes().getNamedItem("andExpression").getNodeValue();
		Vector<ConditionSingle> allCondition = new Vector<>();
		for(int i = 0; i < conditionInformationList.getLength(); i++) {
			Node node = conditionInformationList.item(i);
			if(node.getNodeName().equals(fConditonalStateTag)) {
				ConditionSingle conditionSingle = factory.createConditionSingle();
				Condition condition = factory.createCondition();
				String notCondition = "false";
				if(node.getAttributes().getNamedItem("isNotCondition") != null) {
					notCondition = node.getAttributes().getNamedItem("isNotCondition").getNodeValue();
				}
				if(notCondition.equals("true")) {
					conditionSingle.setNot(conditionSingle);
				} else {
				}
				
				if(node.getAttributes().getNamedItem("xsi:type").getNodeValue().equals(fAttributeConditionType)) {
					Vector<StateAttribute> attributeList = new Vector<>();
					StateValue stateValue = factory.createStateValue();
					extractAttribute(node.getChildNodes(), attributeList, stateValue);
					condition.getStateAttribute().addAll(attributeList);
					condition.setStateValue(stateValue);

				} else if(node.getAttributes().getNamedItem("xsi:type").getNodeValue().equals(fEventFieldConditionType)) {
					String fieldName = node.getAttributes().getNamedItem("fieldName").getNodeValue();
					Node stateValueNode = node.getChildNodes().item(1);
					String type = stateValueNode.getAttributes().getNamedItem("type").getNodeValue();
					String value = stateValueNode.getAttributes().getNamedItem("value").getNodeValue();
					
					EventField eventField = factory.createEventField();
					eventField.setName(fieldName);
					
					StateValue stateValue = factory.createStateValue();
					stateValue.setType(type);
					stateValue.setValue(value);
					
					condition.setField(eventField);
					condition.setStateValue(stateValue);
				}
				conditionSingle.setCondition(condition);
				if(notCondition.equals("true")) {
					conditionSingle.setNot(conditionSingle);
				}
				allCondition.add(conditionSingle);
			} else if (node.getNodeName().equals(fTransitionTag)) { // If node is transition (then and else)
				Vector<StateAttribute> transitionAttributeList = new Vector<>();
				StateValue transitionSateValue = factory.createStateValue();
				if(node.getChildNodes().getLength() == 0) {
					continue;
				}
				extractAttribute(node.getChildNodes().item(1).getChildNodes(), transitionAttributeList, transitionSateValue); // get attribute in stateChange in transition
				if(node.getAttributes().getNamedItem("name").getNodeValue().equals("") || node.getAttributes().getNamedItem("name").getNodeValue().equals("then")) {
					thenStateChange.setStateValue(transitionSateValue);
					thenStateChange.getStateAttribute().addAll(transitionAttributeList);
				} else {
					elseStateChange.setStateValue(transitionSateValue);
					elseStateChange.getStateAttribute().addAll(transitionAttributeList);
				}
			}
		}
		if(allCondition.size() == 1) {
			stateChange.setIf(allCondition.firstElement());
		} else {
			// TODO Condition multiple
		}
		stateChange.setThen(thenStateChange);
		stateChange.setElse(elseStateChange);
		eventHandler.getStateChange().add(stateChange);
	}

	private Node getTargetState(Node transition) {
		String targetState = transition.getAttributes().getNamedItem("state").getNodeValue().substring(1); // Remove "/" at the begining
		String regex = "[/.][/@]*";
		String[] splitedTargetState = targetState.split(regex);
		// Statemachine - state - state index
		int statemachineIndex = Integer.parseInt(splitedTargetState[0]);
		// splitedTargetState[1] is the element type. In this case we don't need it
		int stateIndex = Integer.parseInt(splitedTargetState[2]);
		
		return fStatemachineStatesList.get(statemachineIndex).get(stateIndex);
	}
	
	private boolean buildXmlFile() {
		// State provider
		StateProvider stateProvider = factory.createStateProvider();
		stateProvider.setVersion(new BigInteger("0"));
		stateProvider.setId(xmlID + ".state.provider");
		
		// Head
		HeadProvider headprovider = factory.createHeadProvider();
		
		Label headLabel = factory.createHeadProviderLabel();
		headLabel.setValue(xmlID.replaceAll("[.]", " ") + " state provider");
		
		TraceType headTraceType = factory.createHeadProviderTraceType();
		headTraceType.setId(traceType);
		headprovider.setLabel(headLabel);
		headprovider.getTraceType().add(headTraceType);
		stateProvider.setHead(headprovider);
		
		for(Entry<Integer, Map<Integer, Node>> statemachineStatesEntry : fStatemachineStatesList.entrySet()) {
			Map<Node, Node> conditionalTransition = new HashMap<>();
			for(Entry<Integer, Node> state : statemachineStatesEntry.getValue().entrySet()) {
				if (state.getValue().getAttributes().getNamedItem("xsi:type").getNodeValue().equals(fStateType)) {
					// Defined Value
					DefinedValue definedValue = factory.createDefinedValue();
					definedValue.setName(state.getValue().getAttributes().getNamedItem("name").getNodeValue());
					definedValue.setValue(state.getKey().toString());
					stateProvider.getDefinedValue().add(definedValue);
					
					// Event handler
					Vector<Node> transitions = extractTransitionsNode(state.getValue());
					for(Node transitionNode : transitions) {						
						EventHandler eventHandler = factory.createEventHandler();
						String transitionName = transitionNode.getAttributes().getNamedItem("name").getNodeValue();
						if(fEventHandlerList.containsKey(transitionName)) {
							eventHandler = fEventHandlerList.get(transitionName);
						} else {
							eventHandler.setEventName(transitionNode.getAttributes().getNamedItem("name").getNodeValue());
						}
						Vector<StateChange> transitionStateChanges = extractStateChange(transitionNode);
						eventHandler.getStateChange().addAll(transitionStateChanges);
						
						fEventHandlerList.put(transitionName, eventHandler);
						
						Node transitionTarget = getTargetState(transitionNode);
						if(transitionTarget.getAttributes().getNamedItem("xsi:type").getNodeValue().equals(fConditionalStateType)) {
							conditionalTransition.put(transitionNode, transitionTarget);
						}
					}
				}
			}
			
			for(Entry<Node, Node> conditionalEntry : conditionalTransition.entrySet()) {
				StateChange conditionStateChange = extractStateChangeFromCondition(conditionalEntry.getValue());
				fEventHandlerList.get(conditionalEntry.getKey().getAttributes().getNamedItem("name").getNodeValue()).getStateChange().add(conditionStateChange);
			}
		}
		
		for (Entry<String, EventHandler> eventHandler : fEventHandlerList.entrySet()) {
			stateProvider.getEventHandler().add(eventHandler.getValue());
		}
		
		Tmfxml tmfXml = factory.createTmfxml();
		tmfXml.getTimeGraphViewOrStateProvider().add(stateProvider);
		try {
			JAXBContext context = JAXBContext.newInstance("org.eclipse.tracecompass.tmf.xmlconverter.core.model");
			JAXBElement<Tmfxml> element = factory.createTmfxml(tmfXml);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
			//marshaller.marshal(element, new File("/home/simon/git/tracecompass_statemachine/converted_xml/converted_xml.xml"));
			marshaller.marshal(element, new File("C:\\Users\\Simon\\Downloads\\converted_xml.xml"));
		} catch (JAXBException e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		//String xmlPath = "/home/simon/runtime-Tracecompass/Trace/src/diagrams/kernel_statemachine.diagram";
		String xmlPath = "C:\\Users\\Simon\\Downloads\\kernel_statemachine.diagram";
		File xmlFile = new File(xmlPath);
		TmfGraphitiXmlConverterV2 converter = new TmfGraphitiXmlConverterV2();
		converter.convertXml(xmlFile);
	}

}
