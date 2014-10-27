package org.eclipse.tracecompass.tmf.attributetree.core.model;

public class AttributeValueNode extends AbstractAttributeNode {

	public AttributeValueNode(AbstractAttributeNode parent) {
		super(parent);
		nodeName = "Possible value";
	}
	
	public AttributeValueNode(AbstractAttributeNode parent, String name) {
		super(parent);
		nodeName = name;	
	}

}
