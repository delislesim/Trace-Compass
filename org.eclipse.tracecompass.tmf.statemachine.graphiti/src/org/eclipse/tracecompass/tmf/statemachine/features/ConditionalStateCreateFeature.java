package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import statemachine.ConditionalState;
import statemachine.Statemachine;
import statemachine.StatemachineFactory;

public class ConditionalStateCreateFeature extends AbstractCreateFeature {

	public ConditionalStateCreateFeature(IFeatureProvider fp) {
		super(fp, "Condition", "Creates a new condition");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		Object bo = getBusinessObjectForPictogramElement(context.getTargetContainer().getLink().getPictogramElement());
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
