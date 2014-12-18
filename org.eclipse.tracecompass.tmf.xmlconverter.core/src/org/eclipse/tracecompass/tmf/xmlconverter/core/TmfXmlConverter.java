package org.eclipse.tracecompass.tmf.xmlconverter.core;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.tracecompass.tmf.xmlconverter.core.model.DefinedValue;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.EventHandler;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.HeadProvider;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.ObjectFactory;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.StateChange;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.StateProvider;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.StateValue;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.Tmfxml;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.HeadProvider.Label;
import org.eclipse.tracecompass.tmf.xmlconverter.core.model.HeadProvider.TraceType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TmfXmlConverter implements ITmfXmlConverter {

	private Document fDoc = null;
	private String fStatesTag = "states";
	private String fTransitionsTag = "transitions";
	private List<String> fStatesList = new ArrayList<String>();
	private List<String> fTransitionsList = new ArrayList<String>();
	private Map<String, String> fStatesTransitions = new HashMap<String, String>();
	
	private String traceType = "org.eclipse.tracecompass.tmf.core.development";
	private String xmlID;

	public TmfXmlConverter() {
	}

	@Override
	public File convertDiagram(File xml) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			fDoc = dBuilder.parse(xml);
			fDoc.getDocumentElement().normalize();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			return null;
		}
		
		xmlID = xml.getName().replaceFirst("[.][^.]+$", "").replaceAll("[^a-z0-9A-Z]", ".");

		NodeList statesNodeList = fDoc.getElementsByTagName(fStatesTag);
		for (int i = 0; i < statesNodeList.getLength(); i++) {
			Element state = (Element) statesNodeList.item(i);
			fStatesList.add(state.getAttribute("name"));
		}

		NodeList transitionsNodeList = fDoc.getElementsByTagName(fTransitionsTag);
		for (int i = 0; i < transitionsNodeList.getLength(); i++) {
			Element transition = (Element) transitionsNodeList.item(i);
			fTransitionsList.add(transition.getAttribute("name"));
			String targetState = transition.getAttribute("state");
			String stateNumber = targetState.replaceAll("[^0-9]", "");
			String associatedState = fStatesList.get(Integer
					.parseInt(stateNumber));
			fStatesTransitions.put(transition.getAttribute("name"),
					associatedState);
		}

		System.out.println(fStatesTransitions);

		//return buildXmlFile();
		return buildXmlFileWithXsd();
	}
	
	private File buildXmlFileWithXsd() {
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
		
		// Defined Value
		for(int i = 0; i<fStatesList.size(); i++) {
			DefinedValue definedValue = factory.createDefinedValue();
			definedValue.setName(fStatesList.get(i));
			definedValue.setValue(((Integer)i).toString());
			stateProvider.getDefinedValue().add(definedValue);
		}
		
		// Event handler
		for(int i = 0; i<fTransitionsList.size(); i++) {
			EventHandler eventHandler = factory.createEventHandler();
			eventHandler.setEventName(fTransitionsList.get(i));
			
			// State change
			StateChange statechange = factory.createStateChange();
			
			// State value
			StateValue stateValue = factory.createStateValue();
			stateValue.setType("int");
			stateValue.setValue("$" + fStatesTransitions.get(fTransitionsList.get(i)));
			
			statechange.setStateValue(stateValue);
			eventHandler.getStateChange().add(statechange);
			stateProvider.getEventHandler().add(eventHandler);
		}
		
		Tmfxml tmfXml = factory.createTmfxml();
		tmfXml.getTimeGraphViewOrStateProvider().add(stateProvider);
		File file = new File("/home/simon/git/xml_converter/lttng/org.eclipse.tracecompass.tmf.xmlconverter.core.tests/Diagrams/converted_xml.xml");
		try {
			JAXBContext context = JAXBContext.newInstance("org.eclipse.tracecompass.tmf.xmlconverter.core.model");
			JAXBElement<Tmfxml> element = factory.createTmfxml(tmfXml);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
			marshaller.marshal(element, file);
		} catch (JAXBException e) {
			return null;
		}
		return file;
	}

	private boolean buildXmlFile() {
		Document newXml = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			newXml = dBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			return false;
		}

		// Structure du xml
		// TODO : remplacer les string par des TmfXmlStrings
		Element tmfXml = newXml.createElement("tmfxml");
		Element stateProvider = newXml.createElement("stateProvider");
		Element head = newXml.createElement("head");
		Element traceType = newXml.createElement("traceType");
		Element label = newXml.createElement("label");
		Element definedValue = newXml.createElement("definedValue");
		Element eventHandler = newXml.createElement("eventHandler");
		Element stateChange = newXml.createElement("stateChange");
		Element stateAttribute = newXml.createElement("stateAttribute");
		Element stateValue = newXml.createElement("stateValue");

		newXml.appendChild(tmfXml);
		tmfXml.appendChild(stateProvider);
		stateProvider.appendChild(head);
		stateProvider.setAttribute("version", "0");
		stateProvider.setAttribute("id", xmlID + ".state.provider");
		head.appendChild(traceType);
		traceType.setAttribute("id", this.traceType);
		label.setAttribute("value", xmlID.replaceAll("[.]", " ") + " state provider");
		head.appendChild(label);
		for (int i = 0; i < fStatesList.size(); i++) {
			definedValue = newXml.createElement("definedValue");
			definedValue.setAttribute("name", fStatesList.get(i));
			definedValue.setAttribute("value", "10" + i);
			stateProvider.appendChild(definedValue);
		}

		for (int i = 0; i < fStatesTransitions.size(); i++) {
			eventHandler = newXml.createElement("eventHandler");
			eventHandler.setAttribute("eventName", fTransitionsList.get(i));
			stateProvider.appendChild(eventHandler);
			stateChange = newXml.createElement("stateChange");
			stateAttribute = newXml.createElement("stateAttribute");
			stateValue = newXml.createElement("stateValue");
			eventHandler.appendChild(stateChange);
			stateAttribute.setAttribute("type", "constant");
			stateAttribute.setAttribute("value", "Tasks");
			stateChange.appendChild(stateAttribute);
			stateValue.setAttribute("type", "int");
			stateValue.setAttribute("value", "$" + fStatesTransitions.get(fTransitionsList.get(i)));
			stateChange.appendChild(stateValue);
		}

		try {
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(newXml);
			StreamResult result = new StreamResult(
					new File(
							"/home/simon/git/xml_converter/lttng/org.eclipse.tracecompass.tmf.xmlconverter.core.tests/Diagrams/converted_xml.xml"));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, result);
		} catch (TransformerException e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String xmlPath = "/home/simon/git/xml_converter/lttng/org.eclipse.tracecompass.tmf.xmlconverter.core.tests/Diagrams/exemple_xml.statemachine";
		File xmlFile = new File(xmlPath);
		TmfXmlConverter converter = new TmfXmlConverter();
		converter.convertDiagram(xmlFile);
	}

}
