package org.eclipse.tracecompass.tmf.attributetree.core.model;

public class ConstantAttributeNode extends AbstractAttributeNode {

	public ConstantAttributeNode(AbstractAttributeNode parent) {
		super(parent);
		nodeName = "Constant attribute";
	}
	
	public ConstantAttributeNode(AbstractAttributeNode parent, String name) {
		super(parent);
		nodeName = name;	
	}

}
