package org.eclipse.tracecompass.tmf.attributetree.ui.widgets;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.tracecompass.tmf.attributetree.core.model.AbstractAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeTreePath;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeValueNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.ConstantAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.VariableAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.utils.AttributeTreeXmlUtils;
import org.eclipse.tracecompass.tmf.core.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AttributeTree {
	
	private static AttributeTree INSTANCE = null;
	
	private static AbstractAttributeNode invisibleRoot = null;
	private static Stack<Pair<AbstractAttributeNode, String>> queryNodeStack = new Stack<>();
	private static File currentFile = new File(AttributeTreeXmlUtils.getAttributeTreeXmlFilesPath().append(AttributeTreeXmlUtils.FILE_NAME).toString());
	
	private AttributeTree() {
		
	}
 
	public static AttributeTree getInstance()
	{
		if (INSTANCE == null) {
			INSTANCE = new AttributeTree();
		}
		
		if(invisibleRoot == null) {
			loadXmlTree(currentFile);
			//loadXmlTree(AttributeTreeXmlUtils.getAttributeTreeXmlFile(AttributeTreeXmlUtils.FILE_NAME));
			//loadXmlTree(AttributeTreeXmlUtils.getAttributeTreeXmlFilesPath().append(AttributeTreeXmlUtils.FILE_NAME).toFile());
		}
		
		return INSTANCE;
	}
	
	public void setFile(File newFile) {
		if(!currentFile.equals(newFile) && newFile != null) {
			loadXmlTree(newFile);
			currentFile = newFile;
		}
	}
	
	public File getFile() {
		return currentFile;
	}
	
	public AbstractAttributeNode getNodeFromPath(String path) {
		AbstractAttributeNode currentNode = invisibleRoot;
		for(String nodeName : splitPath(path)) {
			currentNode = searchNode(currentNode, nodeName);
		}
		return currentNode;
	}
	
	public AbstractAttributeNode getRoot() {
		return invisibleRoot;
	}
	
	private static void loadXmlTree(File xmlFile) {
		Document xmlTree = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			xmlTree = dBuilder.parse(xmlFile);
			xmlTree.getDocumentElement().normalize();
		} catch (ParserConfigurationException | SAXException | IOException e) {
		}
		
		if(xmlTree == null) {
			invisibleRoot = new ConstantAttributeNode(null, "root");
			return;
		}
		
		NodeList nodeList = xmlTree.getElementsByTagName("root");
		Node xmlRootNode = nodeList.item(0); // Only one root
		if(invisibleRoot != null) {
			invisibleRoot.removeAllChildren();
			invisibleRoot.setName(((Element)xmlRootNode).getAttribute("name"));
		} else {
			invisibleRoot = new ConstantAttributeNode(null, ((Element)xmlRootNode).getAttribute("name"));
		}
		getTreeFromXml(xmlRootNode, invisibleRoot);
		addQueryToTree();
	}
	
	private static void getTreeFromXml(Node parentNode, AbstractAttributeNode parentAttribute) {
		NodeList childrenNodes = parentNode.getChildNodes();
		for(int i = 0; i < childrenNodes.getLength(); i++) {
			Node childNode = childrenNodes.item(i);
			if(childNode.getNodeType() == Node.TEXT_NODE) {
				continue;
			}
			Element childElement = (Element) childNode;
			AbstractAttributeNode parent = null;
			if (childElement.getAttribute("type").equals(ConstantAttributeNode.class.getSimpleName())) {
				parent = new ConstantAttributeNode(parentAttribute, childElement.getAttribute("name"));
			} else if (childElement.getAttribute("type").equals(VariableAttributeNode.class.getSimpleName())) {
				parent = new VariableAttributeNode(parentAttribute, childElement.getAttribute("name"));
				
				String xpathQuery = childElement.getAttribute("query");
				if(!xpathQuery.equals("")) {
					queryNodeStack.push(new Pair<AbstractAttributeNode, String>(parent, xpathQuery));
				}
			} else if (childElement.getAttribute("type").equals(AttributeValueNode.class.getSimpleName())) {
				parent = new AttributeValueNode(parentAttribute, childElement.getAttribute("name"));
			}
			getTreeFromXml(childNode, parent);
		}
	}
	
	private static void addQueryToTree() {
		while (!queryNodeStack.empty()) {
			Pair<AbstractAttributeNode, String> queryPair = queryNodeStack.pop();
			VariableAttributeNode node = (VariableAttributeNode) queryPair.getFirst();
			String path = queryPair.getSecond();

//			String[] splitedPath = path.split("/");
//			AbstractAttributeNode currentNode = invisibleRoot;
//			for (int i = 2; i < splitedPath.length; i++) { // Skip root + empty string
//				currentNode = searchNode(currentNode, splitedPath[i]);
//			}
			
			AbstractAttributeNode currentNode = invisibleRoot;
			for(String nodeName : splitPath(path)) {
				currentNode = searchNode(currentNode, nodeName);
			}
			
			node.setIsQuery(true);
			node.setQueryPath(new AttributeTreePath(currentNode));
		}
	}
	
	private static AbstractAttributeNode searchNode(AbstractAttributeNode parent, String nodeName) {
		if(!parent.hasChildren()) {
			return parent;
		}
		
		for(AbstractAttributeNode child : parent.getChildren()) {
			if(child.getName().replace(" ", "").equals(nodeName)) {
				return child;
			}
		}
		
		return null;
	}
	
	private static Vector<String> splitPath(String path) {
		String[] splitedPath = path.split("/");
		Vector<String> nodeInPath = new Vector<>();
		for (int i = 2; i < splitedPath.length; i++) { // Skip root + empty string
			nodeInPath.add(splitedPath[i]);
		}
		
		return nodeInPath;
	}
}
