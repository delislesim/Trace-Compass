package org.eclipse.tracecompass.tmf.attributetree.core.model;

public class VariableAttributeNode extends AbstractAttributeNode {
	
	private boolean isQuery = false;
	private AttributeTreePath QueryPath;

	public VariableAttributeNode(AbstractAttributeNode parent) {
		super(parent);
		nodeName = "Variable attribute";
	}
	
	public VariableAttributeNode(AbstractAttributeNode parent, String name) {
		super(parent);
		nodeName = name;	
	}
	
	public void setIsQuery(boolean query) {
		isQuery = query;
	}
	
	public boolean getIsQuery() {
		return isQuery;
	}
	
	public void setQueryPath(AttributeTreePath path) {
		QueryPath = path;
	}
	
	public AttributeTreePath getQueryPath() {
		return QueryPath;
	}
}
