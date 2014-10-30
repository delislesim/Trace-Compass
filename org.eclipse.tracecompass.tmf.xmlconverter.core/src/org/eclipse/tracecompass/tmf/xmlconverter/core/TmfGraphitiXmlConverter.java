package org.eclipse.tracecompass.tmf.xmlconverter.core;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.tracecompass.tmf.xmlconverter.core.model.DefinedValue;
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
	
	private String fStateTag = "statemachine:State";
	private String fInitialStateTag = "statemachine:InitialState";
	private String fFinalStateTag = "statemachine:FinalState";
	private String fTransitionsTag = "statemachine:Transition";
	
	private Map<Integer, Node> fStatesList = new HashMap<Integer, Node>();
	private Map<Integer, Node> fTransitionsList = new HashMap<Integer, Node>();
	
	private String traceType = "org.eclipse.tracecompass.tmf.core.development";
	private String xmlID;
	
	private int positionInXML = 1;

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
		
		NodeList nodeList = fGraphitiXml.getDocumentElement().getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			String nodeName = node.getNodeName();
			if(nodeName.equals(fStateTag) || nodeName.equals(fInitialStateTag) || nodeName.equals(fFinalStateTag)) {
				fStatesList.put(positionInXML, node);
				++positionInXML;
			} else if (nodeName.equals(fTransitionsTag)) {
				fTransitionsList.put(positionInXML, node);
				++positionInXML;
			}
		}
		return buildXmlFile();
	}
	
	private boolean buildXmlFile() {
		ObjectFactory factory = new ObjectFactory();
		
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
		
		// Defined value
		for(Entry<Integer, Node> stateNode : fStatesList.entrySet()) {				
			if(stateNode.getValue().getNodeName().equals(fStateTag)) {
				DefinedValue definedValue = factory.createDefinedValue();
				definedValue.setName(stateNode.getValue().getAttributes().getNamedItem("name").getNodeValue().toUpperCase());
				definedValue.setValue(stateNode.getKey().toString());
				stateProvider.getDefinedValue().add(definedValue);
			}
		}
		// Event handler
		for(Entry<Integer, Node> transitionNode : fTransitionsList.entrySet()) {	
			EventHandler eventHandler = factory.createEventHandler();
			eventHandler.setEventName(transitionNode.getValue().getAttributes().getNamedItem("name").getNodeValue());
			// State change
			NodeListIterable stateChangeList = new NodeListIterable(transitionNode.getValue().getChildNodes());
			for(Node stateChangeNode : stateChangeList) {
				// On est rendu � la fin de la liste
				if(stateChangeNode == null) {
					break;
				}
				StateChange stateChange = factory.createStateChange();
				// State attribute
				NodeListIterable stateAttributeList = new NodeListIterable(stateChangeNode.getChildNodes());
				for (Node stateAttributeNode : stateAttributeList) {
					// On est rendu � la fin de la liste
					if(stateAttributeNode == null) {
						break;
					}
					StateAttribute stateAttribute = factory.createStateAttribute();
					stateAttribute.setType(stateAttributeNode.getAttributes().getNamedItem("type").getNodeValue());
					stateAttribute.setValue(stateAttributeNode.getAttributes().getNamedItem("value").getNodeValue());
					stateChange.getStateAttribute().add(stateAttribute);
				}
				
				// State value
				StateValue stateValue = factory.createStateValue();
				stateValue.setType("int");
				int targetState = Integer.parseInt(transitionNode.getValue().getAttributes().getNamedItem("state").getNodeValue().replaceAll("[/]", ""));
				stateValue.setValue("$" + fStatesList.get(targetState).getAttributes().getNamedItem("name").getNodeValue().toUpperCase());
				stateChange.setStateValue(stateValue);
				eventHandler.getStateChange().add(stateChange);
			}
			stateProvider.getEventHandler().add(eventHandler);
		}
		Tmfxml tmfXml = factory.createTmfxml();
		tmfXml.getTimeGraphViewOrStateProvider().add(stateProvider);
		try {
			JAXBContext context = JAXBContext.newInstance("org.eclipse.tracecompass.tmf.xmlconverter.core.model");
			JAXBElement<Tmfxml> element = factory.createTmfxml(tmfXml);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
			marshaller.marshal(element, new File("C:\\Users\\Simon\\Documents\\Git\\statemachine_trace_analysis\\lttng\\org.eclipse.tracecompass.tmf.xmlconverter.core\\Diagram\\converted_xml.xml"/*"/home/simon/git/statemachine_trace_analysis/converted_xml.xml"*/));
		} catch (JAXBException e) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		//String xmlPath = "/home/simon/runtime-TMF/Trace/src/diagrams/statemachine_exemple.diagram";
		String xmlPath = "C:\\Users\\Simon\\Documents\\Git\\statemachine_trace_analysis\\lttng\\org.eclipse.tracecompass.tmf.xmlconverter.core\\Diagram\\statemachine_exemple.diagram";
		File xmlFile = new File(xmlPath);
		TmfGraphitiXmlConverter converter = new TmfGraphitiXmlConverter();
		converter.convertXml(xmlFile);
	}

}
