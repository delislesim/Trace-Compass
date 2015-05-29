package org.eclipse.tracecompass.tmf.xmlconverter.core;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.tracecompass.tmf.attributetree.core.model.AbstractAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeTree;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeTreePath;
import org.eclipse.tracecompass.tmf.attributetree.core.model.ConstantAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.VariableAttributeNode;
import org.eclipse.tracecompass.tmf.core.util.Pair;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.Condition;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.ConditionMultiple;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.ConditionSingle;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.DefinedValue;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.EventField;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.EventHandler;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.HeadOutput;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.HeadProvider;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.ObjectFactory;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.StateAttribute;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.StateChange;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.StateProvider;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.StateValue;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.TimeGraphView;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.Tmfxml;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.ViewStateAttribute;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.HeadOutput.Analysis;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.HeadProvider.Label;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.HeadProvider.TraceType;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.ViewEntry;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TmfGraphitiXmlConverter implements ITmfXmlConverter {
	
	private Document fGraphitiXml = null;
	public static final String FILE_NAME = "convert_xml.xml";
	
	private String fStatemachineTag = "statemachine:Statemachine";
	private String fStateAttributeTag = "stateAttribute";
	private String fStateValueTag = "stateValue";
	private String fConditonTag = "condition";
	private String fTransitionTag = "transitions";
	
	private String fStateType = "statemachine:State";
	private String fInitialStateType = "statemachine:InitialState";
	private String fConditionalStateType = "statemachine:ConditionalState";
	
	private String fAttributeConditionType = "statemachine:AttributeCondition";
	
	private Map<Integer, Node> fStatemachineList = new HashMap<>();
	private Map<Integer, Map<Integer, Node>> fStatemachineStatesList = new HashMap<>();
	
	private Map<String, EventHandler> fEventHandlerList = new HashMap<>();
	
	private String traceType = "org.eclipse.tracecompass.tmf.core.development";
	private String xmlID;
	
	private int statemachinePositionInXml = 1;
	private int statePositionInStatemachine = 0;
	
	private ObjectFactory factory = new ObjectFactory();

	@Override
	public File convertDiagram(File diagramFile) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			fGraphitiXml = dBuilder.parse(diagramFile);
			fGraphitiXml.getDocumentElement().normalize();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			return null;
		}
		
		xmlID = diagramFile.getName().replaceFirst("[.][^.]+$", "").replaceAll("[^a-z0-9A-Z]", ".");
		
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
		return buildXmlFile(diagramFile.getPath().replace(".diagram", ".xml"));
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
				
				Vector<StateAttribute> stateAttributeList = new Vector<>();
				StateValue stateValue = factory.createStateValue();
				NodeList stateChangeInformationList = null;
				if(node.getNodeName().equals(fConditonTag)) {
					stateChangeInformationList = stateChangeNodeList;
				} else {
					stateChangeInformationList = stateChangeNode.getChildNodes();
				}
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
				String stateValueType;
				if(node.getAttributes().getNamedItem("type") == null) {
					stateValueType = "int"; // Default value not written in the diagram file
 				} else {
 					stateValueType = node.getAttributes().getNamedItem("type").getNodeValue();
					if(stateValueType.equals("definedState")) {
						stateValueType = "int";
					}
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
			} else if(node.getNodeName().equals(fConditonTag)) {
				conditions.add(node);
			}
		}
		
		StateChange thenStateChange = null;
		StateChange elseStateChange = null;
		for(Node transition : transitions) {
			Vector<StateChange> transitionStateChange = extractStateChange(transition);
			String transitionName = transition.getAttributes().getNamedItem("name").getNodeValue();
			if(!transitionStateChange.isEmpty()) {
				if(transitionName.equals("") || transitionName.equals("then")) {
					thenStateChange = transitionStateChange.firstElement();
				} else {
					elseStateChange = transitionStateChange.firstElement();
				}
			} else {
				Node targetCondtion = getTargetState(transition);
				if(transitionName.equals("") || transitionName.equals("then")) {
					thenStateChange = extractStateChangeFromCondition(targetCondtion);
				} else {
					elseStateChange = extractStateChangeFromCondition(targetCondtion);
				}
			}
		}
		
		Vector<Pair<Condition, Boolean>> ifCondition = new Vector<>();
		Boolean notCondition = false;
		for(Node conditionNode : conditions) {
			if(conditionNode.getAttributes().getNamedItem("isNotCondition") != null && conditionNode.getAttributes().getNamedItem("isNotCondition").getNodeValue().equals("true")) {
				notCondition = true;
			} else {
				notCondition = false;
			}
			
			if(conditionNode.getAttributes().getNamedItem("xsi:type").getNodeValue().equals(fAttributeConditionType)) {
				StateChange conditionStateChange = factory.createStateChange();
				conditionStateChange = extractStateChange(conditionNode).firstElement();
				Condition attributeCondition = new Condition();
				attributeCondition.getStateAttribute().addAll(conditionStateChange.getStateAttribute());
				attributeCondition.setStateValue(conditionStateChange.getStateValue());
				ifCondition.add(new Pair<Condition, Boolean>(attributeCondition, notCondition));
			} else {
				EventField eventField = new EventField();
				String fieldName = conditionNode.getAttributes().getNamedItem("fieldName").getNodeValue();
				eventField.setName(fieldName);				
				
				Node stateValueNode = conditionNode.getChildNodes().item(1);
				String type;
				if(stateValueNode.getAttributes().getNamedItem("type") == null) {
					type = "int"; // Default value not written in the diagram file
				} else {
					type = stateValueNode.getAttributes().getNamedItem("type").getNodeValue();
				}
				String value = stateValueNode.getAttributes().getNamedItem("value").getNodeValue();
				StateValue stateValue = factory.createStateValue();
				stateValue.setType(type);
				stateValue.setValue(value);
				
				Condition fieldCondition = new Condition();
				fieldCondition.setField(eventField);
				fieldCondition.setStateValue(stateValue);
				ifCondition.add(new Pair<Condition, Boolean>(fieldCondition, notCondition));
			}
		}
		
		StateChange stateChange = factory.createStateChange();
		ConditionSingle conditionSingle = new ConditionSingle();
		ConditionSingle notConditionSingle = new ConditionSingle();
		if(ifCondition.size() == 1) {
			if(ifCondition.firstElement().getSecond()) {
				notConditionSingle.setCondition(ifCondition.firstElement().getFirst());
				conditionSingle.setNot(notConditionSingle);
			} else {
				conditionSingle.setCondition(ifCondition.firstElement().getFirst());
			}
			stateChange.setIf(conditionSingle);
		} else {
			Boolean isAndExpression = true;
			if(condition.getAttributes().getNamedItem("andExpression") != null && condition.getAttributes().getNamedItem("andExpression").getNodeValue().equals("false")) {
				isAndExpression = false;
			} else {
				isAndExpression = true;
			}
			ConditionMultiple conditionMultiple = new ConditionMultiple();
			for(Pair<Condition, Boolean> multipleCondition : ifCondition) {
				Condition AndOrCondition = multipleCondition.getFirst();
				if(multipleCondition.getSecond()) {
					notConditionSingle.setCondition(AndOrCondition);
					JAXBElement<ConditionSingle> notConditionElement = factory.createConditionMultipleNot(notConditionSingle);
					conditionMultiple.getConditionAndOrAndAnd().add(notConditionElement);
				} else {
					JAXBElement<Condition> conditionElement = factory.createConditionMultipleCondition(AndOrCondition);
					conditionMultiple.getConditionAndOrAndAnd().add(conditionElement);
				}
			}
			
//			// TODO Test only
//			Condition condition1 = ifCondition.get(0).getFirst();
//			Condition condition2 = ifCondition.get(1).getFirst();
//			Condition condition3 = ifCondition.get(2).getFirst();
//			Condition condition4 = ifCondition.get(3).getFirst();
//			
//			// (0 AND 1) OR (2 AND 3)
//			ConditionMultiple condition1_2 = new ConditionMultiple();
//			JAXBElement<Condition> element1 = factory.createConditionMultipleCondition(condition1);
//			JAXBElement<Condition> element2 = factory.createConditionMultipleCondition(condition2);
//			condition1_2.getConditionAndOrAndAnd().add(element1);
//			condition1_2.getConditionAndOrAndAnd().add(element2);
//			
//			ConditionMultiple condition2_3 = new ConditionMultiple();
//			JAXBElement<Condition> element3 = factory.createConditionMultipleCondition(condition3);
//			JAXBElement<Condition> element4 = factory.createConditionMultipleCondition(condition4);
//			condition2_3.getConditionAndOrAndAnd().add(element3);
//			condition2_3.getConditionAndOrAndAnd().add(element4);
//			
//			ConditionMultiple conditionAll = new ConditionMultiple();
//			JAXBElement<ConditionMultiple> element1_2 = factory.createConditionMultipleAnd(condition1_2);
//			JAXBElement<ConditionMultiple> element3_4 = factory.createConditionMultipleAnd(condition2_3);
//			conditionAll.getConditionAndOrAndAnd().add(element1_2);
//			conditionAll.getConditionAndOrAndAnd().add(element3_4);
//			conditionSingle.setOr(conditionAll);
			
			
			if(isAndExpression) {
				conditionSingle.setAnd(conditionMultiple);
			} else {
				conditionSingle.setOr(conditionMultiple);
			}
			stateChange.setIf(conditionSingle);
		}
		
		stateChange.setThen(thenStateChange);
		stateChange.setElse(elseStateChange);
		
		return stateChange;
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
	
	private File buildXmlFile(String xmlFilePath) {
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
			Map<String, Node> conditionalTransition = new HashMap<>();
			for(Entry<Integer, Node> state : statemachineStatesEntry.getValue().entrySet()) {
				if (state.getValue().getAttributes().getNamedItem("xsi:type").getNodeValue().equals(fStateType)) {
					// Defined Value
					DefinedValue definedValue = factory.createDefinedValue();
					definedValue.setName(state.getValue().getAttributes().getNamedItem("name").getNodeValue());
					Integer definedValueValue;
					if((state.getKey() & ((2 << 20)-1)) == state.getKey()) {
						definedValueValue = (statemachineStatesEntry.getKey() << 20) + state.getKey();
					} else {
						definedValueValue = 50000;
					}
					definedValue.setValue(definedValueValue.toString());
					stateProvider.getDefinedValue().add(definedValue);
				}
				
				if (state.getValue().getAttributes().getNamedItem("xsi:type").getNodeValue().equals(fStateType) || state.getValue().getAttributes().getNamedItem("xsi:type").getNodeValue().equals(fInitialStateType)) {
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
							conditionalTransition.put(transitionName, transitionTarget);
						}
					}
				}
			}
			
			for(Entry<String, Node> conditionalEntry : conditionalTransition.entrySet()) {
				StateChange conditionStateChange = extractStateChangeFromCondition(conditionalEntry.getValue());
				fEventHandlerList.get(conditionalEntry.getKey()).getStateChange().add(conditionStateChange);
			}
		}
		
		for (Entry<String, EventHandler> eventHandler : fEventHandlerList.entrySet()) {
			stateProvider.getEventHandler().add(eventHandler.getValue());
		}
		
		// Create XY views
		// TODO
		
		Tmfxml tmfXml = factory.createTmfxml();
		tmfXml.getTimeGraphViewOrStateProvider().add(stateProvider);
		// Create control flow views
		tmfXml.getTimeGraphViewOrStateProvider().addAll(createTimeGraphView());
		File xmlFile = new File(xmlFilePath);
		try {
			JAXBContext context = JAXBContext.newInstance("org.eclipse.tracecompass.tmf.xmlconverter.core.model");
			JAXBElement<Tmfxml> tmfXmlElement = factory.createTmfxml(tmfXml);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
			marshaller.marshal(tmfXmlElement, xmlFile);
			//marshaller.marshal(tmfXmlElement, new File("/home/simon/git/tracecompass_statemachine/converted_xml/converted_xml.xml"));
			//marshaller.marshal(element, new File("C:\\Users\\Simon\\Downloads\\converted_xml.xml"));
		} catch (JAXBException e) {
			return null;
		}
		return xmlFile;
	}
	
	private List<TimeGraphView> createTimeGraphView() {
		List<TimeGraphView> timeGraphViewList = new ArrayList<>();
		for (Entry<Integer, Map<Integer, Node>> statemachineStatesEntry : fStatemachineStatesList.entrySet()) {
			TimeGraphView timeGraphView = factory.createTimeGraphView();
			timeGraphView.setId(xmlID + ".view." + fStatemachineList.get(statemachineStatesEntry.getKey()).getAttributes().getNamedItem("name").getNodeValue().replace(" ", ""));

			HeadOutput timeGraphViewHead = factory.createHeadOutput();
			Analysis analysis = factory.createHeadOutputAnalysis();
			analysis.setId(xmlID + ".state.provider");
			org.eclipse.tracecompass.tmf.xmlconverter.core.model.HeadOutput.Label label = factory.createHeadOutputLabel();
			label.setValue(xmlID.replaceAll("[.]", " ") + " view");
			timeGraphViewHead.getAnalysis().add(analysis);
			timeGraphViewHead.setLabel(label);
			timeGraphView.setHead(timeGraphViewHead);

			for (Entry<Integer, Node> state : statemachineStatesEntry.getValue().entrySet()) {
				if (state.getValue().getAttributes().getNamedItem("xsi:type").getNodeValue().equals(fStateType)) {
					// Defined Value
					DefinedValue definedValue = factory.createDefinedValue();
					definedValue.setName(state.getValue().getAttributes().getNamedItem("name").getNodeValue());
					Integer definedValueValue;
					if ((state.getKey() & ((2 << 20) - 1)) == state.getKey()) {
						definedValueValue = (statemachineStatesEntry.getKey() << 20) + state.getKey();
					} else {
						definedValueValue = 50000;
					}
					definedValue.setValue(definedValueValue.toString());

					Node colorNode = state.getValue().getAttributes().getNamedItem("stateColor");
					if (colorNode != null) {
						definedValue.setColor(colorNode.getNodeValue());
					} else {
						definedValue.setColor("#FFFFFF");
					}
					timeGraphView.getDefinedValue().add(definedValue);
				}
			}

			// Get the associated path from statemachine
			String statemachinePath = fStatemachineList.get(statemachineStatesEntry.getKey()).getAttributes().getNamedItem("associatedAttribute").getNodeValue();
			String statemachineTree = fStatemachineList.get(statemachineStatesEntry.getKey()).getAttributes().getNamedItem("associatedTree").getNodeValue();
			AbstractAttributeNode leafNode = AttributeTree.getInstance().getNodeFromPath(new File(statemachineTree), statemachinePath);
			AttributeTreePath treePath = new AttributeTreePath(leafNode);
			Vector<AbstractAttributeNode> pathVector = treePath.getPath();
			
			String path = "";
			for(int i = pathVector.size() - 2; i >= 0; i--) { //Skip root element
				AbstractAttributeNode node = pathVector.get(i);
				if ( node instanceof ConstantAttributeNode) {
					path += (node.getName() + "/");
				} else if (node instanceof VariableAttributeNode) {
					path += "*";
					break;
				}
			}
			
			ViewEntry viewEntry = factory.createViewEntry();
			viewEntry.setPath(path);
			
			ViewStateAttribute viewStateAttribute = factory.createViewStateAttribute();
			viewStateAttribute.setType("constant");
			viewStateAttribute.setValue(pathVector.get(0).getName());
			viewEntry.setDisplay(viewStateAttribute);
			// TODO Entry name ?
			
			timeGraphView.getEntry().add(viewEntry);

			timeGraphViewList.add(timeGraphView);
		}
		return timeGraphViewList;
	}

//	public static void main(String[] args) {
//		String xmlPath = "/home/esideli/Downloads/tracecompass_rcp_backup/Tracing/Statemachine/Diagrams/RequestAnalysis.diagram"; //"/home/simon/runtime-Tracecompass/Trace/Statemachine/Diagrams/kernel_statemachine.diagram";
//		//String xmlPath = "C:\\Users\\Simon\\Downloads\\kernel_statemachine.diagram";
//		File xmlFile = new File(xmlPath);
//		TmfGraphitiXmlConverter converter = new TmfGraphitiXmlConverter();
//		File convertedFile = converter.convertDiagram(xmlFile);
//		if(convertedFile.exists()) {
//			Boolean exist = true;
//		}
//	}

}
