package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;

import statemachine.ConditionalState;
import statemachine.Statemachine;
import statemachine.StatemachineFactory;

public class ConditionalStateCreateFeature extends AbstractCreateFeature {

	public ConditionalStateCreateFeature(IFeatureProvider fp) {
		super(fp, "Condition", "Creates a new condition");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		PictogramLink pictogramLink = context.getTargetContainer().getLink();
		// If the target container is not a pictogram (ex: the diagram)
		if(pictogramLink == null) {
			return false;
		}
		
		Object bo = getBusinessObjectForPictogramElement(pictogramLink.getPictogramElement());
		return bo instanceof Statemachine;
	}

	@Override
	public Object[] create(ICreateContext context) {
		ConditionalState conditionalState = StatemachineFactory.eINSTANCE.createConditionalState();
		conditionalState.setName("Condition");
		Statemachine bo = (Statemachine) getBusinessObjectForPictogramElement(context.getTargetContainer().getLink().getPictogramElement());
		bo.getStates().add(conditionalState);
		addGraphicalRepresentation(context, conditionalState);
		getFeatureProvider().getDirectEditingInfo().setActive(true);
		return new Object[] { conditionalState };
	}

}
