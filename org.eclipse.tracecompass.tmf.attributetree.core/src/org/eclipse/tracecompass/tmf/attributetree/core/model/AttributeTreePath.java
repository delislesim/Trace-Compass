package org.eclipse.tracecompass.tmf.attributetree.core.model;

import java.util.Vector;

import statemachine.StateAttribute;
import statemachine.StateAttributeType;
import statemachine.StatemachineFactory;

public class AttributeTreePath {
	
	private Vector<AbstractAttributeNode> path = new Vector<AbstractAttributeNode>();
	
	public AttributeTreePath(AbstractAttributeNode leaf) {
		buildTreePath(leaf);
	}
	
	private void buildTreePath(AbstractAttributeNode leafNode) {
		path.add(leafNode);
		if(leafNode.getParent() == null) {
			return;
		}
		buildTreePath(leafNode.getParent());
	}
	
	public Vector<AbstractAttributeNode> getPath() {
		return path;
	}
	
	public Vector<StateAttribute> getAllStateAttribute() {
		Vector<StateAttribute> stateAttributeList = new Vector<StateAttribute>();
		for(int i = path.size()-2; i >= 0; i--) { // -2 because of the invisible root
			StateAttribute stateAttribute = StatemachineFactory.eINSTANCE.createStateAttribute();
			stateAttribute.setValue(path.get(i).getName());
			StateAttributeType stateAttributeType;
			if(path.get(i) instanceof VariableAttributeNode) {	
				VariableAttributeNode variableNode = (VariableAttributeNode)path.get(i);
				if(variableNode.getIsQuery()) {
					stateAttributeType = StateAttributeType.QUERY;
					stateAttribute.getStateAttributeQuery().addAll(variableNode.getQueryPath().getAllStateAttribute());
				} else {
					stateAttributeType = StateAttributeType.EVENT_FIELD;
				}
			} else {
				stateAttributeType = StateAttributeType.CONSTANT;
			}
			stateAttribute.setType(stateAttributeType);
			stateAttributeList.add(stateAttribute);
		}
		return stateAttributeList;
	}
	
	public String getPathFromAttributeTreePath() {
		String xPath = "";
		for(int i = path.size()-1; i >= 0; i--) {
			xPath += "/" + path.get(i).getName().replace(" ", "");
		}
		return xPath;
	}
}
