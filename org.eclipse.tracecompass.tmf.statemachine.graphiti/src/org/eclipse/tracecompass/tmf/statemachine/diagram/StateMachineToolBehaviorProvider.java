package org.eclipse.tracecompass.tmf.statemachine.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonPadData;

public class StateMachineToolBehaviorProvider extends DefaultToolBehaviorProvider {
	
	public StateMachineToolBehaviorProvider(IDiagramTypeProvider dtp) {
        super(dtp);
    }
	
	@Override
	public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {
		IContextButtonPadData contextButtonData = super.getContextButtonPad(context);
		PictogramElement pe = context.getPictogramElement();
		CreateConnectionContext createConnectionContext = new CreateConnectionContext();
		createConnectionContext.setSourcePictogramElement(pe);
		Anchor anchor = null;
		if(pe instanceof Anchor) {
			anchor = (Anchor) pe;
		} else if(pe instanceof AnchorContainer) {
			anchor = Graphiti.getPeService().getChopboxAnchor((AnchorContainer) pe);
		}
		createConnectionContext.setSourceAnchor(anchor);
		
	    ContextButtonEntry createTransitionButton = new ContextButtonEntry(null, context);
	    createTransitionButton.setText("Start connection");
	    createTransitionButton.setIconId(StateMachineImageProvider.TRANSITION_ICON);
	    ICreateConnectionFeature[] features = getFeatureProvider().getCreateConnectionFeatures();
	    for (ICreateConnectionFeature feature : features) {
	        if (feature.isAvailable(createConnectionContext) && feature.canStartConnection(createConnectionContext))
	        	createTransitionButton.addDragAndDropFeature(feature);
	    }
	 
	    if (createTransitionButton.getDragAndDropFeatures().size() > 0) {
	    	contextButtonData.getDomainSpecificContextButtons().add(createTransitionButton);
	    }
		
		return contextButtonData;
	}
}
