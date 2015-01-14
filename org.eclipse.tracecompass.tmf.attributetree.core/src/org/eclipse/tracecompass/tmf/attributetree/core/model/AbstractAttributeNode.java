package org.eclipse.tracecompass.tmf.attributetree.core.model;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class AbstractAttributeNode {
	
	private List<AbstractAttributeNode> childNodes;
	private AbstractAttributeNode parentNode;
	
	protected String nodeName;
	
	public AbstractAttributeNode(AbstractAttributeNode parent) {
		parentNode = parent;
		childNodes = new ArrayList<AbstractAttributeNode>();
		if(parentNode != null) {
			parentNode.addChild(this);
		}		
	}

	public String getName() {
		return nodeName;
	}

	public void setName(String name) {
		nodeName = name;
	}

	public List<AbstractAttributeNode> getChildren() {
		return childNodes;
	}

	public AbstractAttributeNode getParent() {
		return parentNode;
	}

	public boolean hasChildren() {
		return !childNodes.isEmpty();
	}

	public void addChild(AbstractAttributeNode child) {
		if (child != null) {
			childNodes.add(child);
		}
	}

	public boolean removeChild(AbstractAttributeNode child) {
		if (child != null) {
			return childNodes.remove(child);
		}
		return false;
	}
	
	public void removeAllChildren() {
		childNodes.clear();
	}
	
	public Element createElement(AbstractAttributeNode rootNode, Document xml) {
		Element node = xml.createElement(rootNode.getName().replace(" ", ""));
		node.setAttribute("type", rootNode.getClass().getSimpleName());
		node.setAttribute("name", rootNode.getName());
		if(rootNode instanceof VariableAttributeNode && ((VariableAttributeNode) rootNode).getIsQuery()) {
			node.setAttribute("query", ((VariableAttributeNode) rootNode).getQueryPath().getPathFromAttributeTreePath());
		}
		for(AbstractAttributeNode child : rootNode.getChildren()) {
			node.appendChild(createElement(child, xml));
		}
		return node;
	}

}
