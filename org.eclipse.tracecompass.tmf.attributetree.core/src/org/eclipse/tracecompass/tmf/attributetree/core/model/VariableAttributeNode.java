package org.eclipse.tracecompass.tmf.attributetree.core.model;

public class VariableAttributeNode extends AbstractAttributeNode {

	public VariableAttributeNode(AbstractAttributeNode parent) {
		super(parent);
		nodeName = "Variable attribute";
	}
	
	public VariableAttributeNode(AbstractAttributeNode parent, String name) {
		super(parent);
		nodeName = name;	
	}

}
