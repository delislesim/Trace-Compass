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

public class TmfGraphitiXmlConverter implements ITmfXmlConverter {
	
	private Document fGraphitiXml = null;
	public static final String FILE_NAME = "convert_xml.xml";
	
//	private String fStateTag = "statemachine:State";
//	private String fInitialStateTag = "statemachine:InitialState";
//	private String fFinalStateTag = "statemachine:FinalState";
//	private String fTransitionsTag = "statemachine:Transition";
	private String fStatemachineTag = "statemachine:Statemachine";
	private String fStateAttributeTag = "stateAttribute";
	private String fStateValueTag = "stateValue";
	private String fConditonalStateTag = "condition";
	private String fTransitionTag = "transitions";
	
//	private Map<Integer, Node> fStatesList = new HashMap<Integer, Node>();
//	private Map<Integer, Node> fTransitionsList = new HashMap<Integer, Node>();
	
	private Map<Integer, Node> fStatemachineList = new HashMap<>();
	private Map<Integer, Node> fStateList = new HashMap<>();
	
	private Map<String, EventHandler> fEventHandlerList = new HashMap<>();
	
	private String fStateType = "statemachine:State";
	private String fConditionalStateType = "statemachine:ConditionalState";
	
	private String fAttributeConditionType = "statemachine:AttributeCondition";
	private String fEventFieldConditionType = "statemachine:FieldCondition";
	
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
			extractStateInformation(statemachineNode.getValue());
			statePositionInStatemachine = 0;
		}
		
		return buildXmlFile();
	}
	
	private void extractStateInformation(Node parentStatemachine) {		
		NodeList stateNodeList = parentStatemachine.getChildNodes();
		for (int i = 0; i < stateNodeList.getLength(); i++) {
			Node stateNode = stateNodeList.item(i);
			if(stateNode.getNodeType() == Node.TEXT_NODE) {
				continue;
			}
			
			fStateList.put(statePositionInStatemachine, stateNode);
			++statePositionInStatemachine;
			/*if(stateNode.getAttributes().getNamedItem("xsi:type").getNodeValue().equals(fStateType)) {
				fStateList.put(statePositionInStatemachine, stateNode);
				++statePositionInStatemachine;
				extractTransitionInformation(stateNode);
			} else if(stateNode.getAttributes().getNamedItem("xsi:type").getNodeValue().equals(fConditionalStateType)) {
				fConditionalStateList.put(statePositionInStatemachine, stateNode);
				++statePositionInStatemachine;
			}*/
		}
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
	
	private void extractTransitionInformation(Node transition, EventHandler eventHandler) {
		NodeList stateChangeNodeList = transition.getChildNodes();
		for (int i = 0; i < stateChangeNodeList.getLength(); i++) {
			Node stateChangeNode = stateChangeNodeList.item(i);
			if(stateChangeNode.getNodeType() == Node.TEXT_NODE) {
				continue;
			}
			
			NodeList stateChangeInformationList = stateChangeNode.getChildNodes();
			Vector<StateAttribute> stateAttributeList = new Vector<>();
			StateValue stateValue = factory.createStateValue();
			extractAttribute(stateChangeInformationList, stateAttributeList, stateValue);
//			for(int j = 0; j < stateChangeInformationList.getLength(); j++) {
//				Node node = stateChangeInformationList.item(j);
//				if(node.getNodeName().equals(fStateAttributeTag)) {
//					StateAttribute stateAttribute = factory.createStateAttribute();
//					String stateAttributeType = node.getAttributes().getNamedItem("type").getNodeValue();
//					if(stateAttributeType.equals("query")) {
//						stateAttribute = stateAttributeQuery(node);
//					} else {
//						stateAttribute.setType(stateAttributeType);
//						stateAttribute.setValue(node.getAttributes().getNamedItem("value").getNodeValue());
//					}
//					stateChange.getStateAttribute().add(stateAttribute);
//				} else if(node.getNodeName().equals(fStateValueTag)) {
//					StateValue stateValue = factory.createStateValue();
//					String stateValueType = node.getAttributes().getNamedItem("type").getNodeValue();
//					if(stateValueType.equals("definedState")) {
//						stateValueType = "int";
//					}
//					stateValue.setType(stateValueType);
//					stateValue.setValue(node.getAttributes().getNamedItem("value").getNodeValue());
//					stateChange.setStateValue(stateValue);
//				}
//			}
			StateChange stateChange = factory.createStateChange();
			stateChange.getStateAttribute().addAll(stateAttributeList);
			stateChange.setStateValue(stateValue);
			eventHandler.getStateChange().add(stateChange);
		}
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
				String notCondition = node.getAttributes().getNamedItem("isNotCondition").getNodeValue();
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

				} else {
					String fieldName = node.getAttributes().getNamedItem("fieldName").getNodeValue();
					Node stateValueNode = node.getChildNodes().item(0);
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
			} else { // If node is transition (then and else)
				Vector<StateAttribute> transitionAttributeList = new Vector<>();
				StateValue transitionSateValue = factory.createStateValue();
				// TODO node de text
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
		String elementInStatemachine = splitedTargetState[1];
		int stateIndex = Integer.parseInt(splitedTargetState[2]);
		
		// TODO Fonctionne pour l'instant
		return fStateList.get(stateIndex);
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
		
		for(Entry<Integer, Node> stateEntry : fStateList.entrySet()) {
			if (stateEntry.getValue().getAttributes().getNamedItem("xsi:type").getNodeValue().equals(fStateType)) {
				// Defined value
				DefinedValue definedValue = factory.createDefinedValue();
				definedValue.setName(stateEntry.getValue().getAttributes().getNamedItem("name").getNodeValue());
				definedValue.setValue(stateEntry.getKey().toString());
				stateProvider.getDefinedValue().add(definedValue);
				
				// Event handler
				for(Node transitionNode : extractTransitionsNode(stateEntry.getValue())) {
					EventHandler eventHandler = factory.createEventHandler();
					String transitionName = transitionNode.getAttributes().getNamedItem("name").getNodeValue();
					if(!fEventHandlerList.containsKey(transitionName)) {
						eventHandler.setEventName(transitionName);
					} else {
						eventHandler = fEventHandlerList.get(transitionName);
					}
					
					if(getTargetState(transitionNode).getAttributes().getNamedItem("xsi:type").getNodeValue().equals(fStateType)) {
						extractTransitionInformation(transitionNode, eventHandler);
						fEventHandlerList.put(transitionName, eventHandler);
					} else {
						extractTransitionInformation(transitionNode, eventHandler);
						extractCondition(getTargetState(transitionNode), eventHandler);
						fEventHandlerList.put(transitionName, eventHandler);
					}
				}
			}
		}
		
		for(Entry<String, EventHandler> eventHandler : fEventHandlerList.entrySet()) {
			stateProvider.getEventHandler().add(eventHandler.getValue());
		}
		
//		// State provider
//		StateProvider stateProvider = factory.createStateProvider();
//		stateProvider.setVersion(new BigInteger("0"));
//		stateProvider.setId(xmlID + ".state.provider");
//		
//		// Head
//		HeadProvider headprovider = factory.createHeadProvider();
//		
//		Label headLabel = factory.createHeadProviderLabel();
//		headLabel.setValue(xmlID.replaceAll("[.]", " ") + " state provider");
//		
//		TraceType headTraceType = factory.createHeadProviderTraceType();
//		headTraceType.setId(traceType);
//		headprovider.setLabel(headLabel);
//		headprovider.getTraceType().add(headTraceType);
//		stateProvider.setHead(headprovider);
//		
//		// Defined value
//		for(Entry<Integer, Node> stateNode : fStatesList.entrySet()) {				
//			if(stateNode.getValue().getNodeName().equals(fStateTag)) {
//				DefinedValue definedValue = factory.createDefinedValue();
//				definedValue.setName(stateNode.getValue().getAttributes().getNamedItem("name").getNodeValue().toUpperCase());
//				definedValue.setValue(stateNode.getKey().toString());
//				stateProvider.getDefinedValue().add(definedValue);
//			}
//		}
//		// Event handler
//		for(Entry<Integer, Node> transitionNode : fTransitionsList.entrySet()) {	
//			EventHandler eventHandler = factory.createEventHandler();
//			eventHandler.setEventName(transitionNode.getValue().getAttributes().getNamedItem("name").getNodeValue());
//			// State change
//			NodeListIterable stateChangeList = new NodeListIterable(transitionNode.getValue().getChildNodes());
//			for(Node stateChangeNode : stateChangeList) {
//				// On est rendu � la fin de la liste
//				if(stateChangeNode == null) {
//					break;
//				}
//				StateChange stateChange = factory.createStateChange();
//				// State attribute
//				NodeListIterable stateAttributeList = new NodeListIterable(stateChangeNode.getChildNodes());
//				for (Node stateAttributeNode : stateAttributeList) {
//					// On est rendu � la fin de la liste
//					if(stateAttributeNode == null) {
//						break;
//					}
//					StateAttribute stateAttribute = factory.createStateAttribute();
//					stateAttribute.setType(stateAttributeNode.getAttributes().getNamedItem("type").getNodeValue());
//					stateAttribute.setValue(stateAttributeNode.getAttributes().getNamedItem("value").getNodeValue());
//					stateChange.getStateAttribute().add(stateAttribute);
//				}
//				
//				// State value
//				StateValue stateValue = factory.createStateValue();
//				stateValue.setType("int");
//				int targetState = Integer.parseInt(transitionNode.getValue().getAttributes().getNamedItem("state").getNodeValue().replaceAll("[/]", ""));
//				stateValue.setValue("$" + fStatesList.get(targetState).getAttributes().getNamedItem("name").getNodeValue().toUpperCase());
//				stateChange.setStateValue(stateValue);
//				eventHandler.getStateChange().add(stateChange);
//			}
//			stateProvider.getEventHandler().add(eventHandler);
//		}
		Tmfxml tmfXml = factory.createTmfxml();
		tmfXml.getTimeGraphViewOrStateProvider().add(stateProvider);
		try {
			JAXBContext context = JAXBContext.newInstance("org.eclipse.tracecompass.tmf.xmlconverter.core.model");
			JAXBElement<Tmfxml> element = factory.createTmfxml(tmfXml);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
			marshaller.marshal(element, new File("/home/simon/git/tracecompass_statemachine/converted_xml/converted_xml.xml"));
			//marshaller.marshal(element, new File("C:\\Users\\Simon\\Documents\\Git\\statemachine_trace_analysis\\lttng\\org.eclipse.tracecompass.tmf.xmlconverter.core\\Diagram\\converted_xml.xml"));
		} catch (JAXBException e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String xmlPath = "/home/simon/runtime-Tracecompass/Trace/src/diagrams/kernel_statemachine.diagram";
		//String xmlPath = "C:\\Users\\Simon\\Documents\\Git\\statemachine_trace_analysis\\lttng\\org.eclipse.tracecompass.tmf.xmlconverter.core\\Diagram\\statemachine_exemple.diagram";
		File xmlFile = new File(xmlPath);
		TmfGraphitiXmlConverter converter = new TmfGraphitiXmlConverter();
		converter.convertXml(xmlFile);
	}

}
