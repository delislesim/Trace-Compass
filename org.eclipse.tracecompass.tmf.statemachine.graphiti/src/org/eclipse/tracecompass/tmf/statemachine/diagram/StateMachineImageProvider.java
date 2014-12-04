package org.eclipse.tracecompass.tmf.statemachine.diagram;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class StateMachineImageProvider extends AbstractImageProvider {
	
    private static final String locationPrefix = "org.eclipse.tracecompass.tmf.statemachine.graphiti.";
	 
    public static final String TRANSITION_ICON = locationPrefix + "transistionIcon";

	@Override
	public void addAvailableImages() {
	        addImageFilePath(TRANSITION_ICON, "Icons/transitionIcon10.gif");
	}

}
