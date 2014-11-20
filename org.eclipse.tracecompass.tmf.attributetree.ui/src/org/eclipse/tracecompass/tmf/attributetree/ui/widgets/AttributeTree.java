package org.eclipse.tracecompass.tmf.attributetree.ui.widgets;

public class AttributeTree {
	
	private static AttributeTree INSTANCE = null;
	
	private AttributeTree() {
		
	}
 
	public static AttributeTree getInstance()
	{			
		if (INSTANCE == null) {
			INSTANCE = new AttributeTree();
		}
		
		return INSTANCE;
	}
}
