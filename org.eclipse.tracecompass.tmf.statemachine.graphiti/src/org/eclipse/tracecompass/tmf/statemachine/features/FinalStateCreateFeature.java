package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import statemachine.FinalState;
import statemachine.Statemachine;
import statemachine.StatemachineFactory;

public class FinalStateCreateFeature extends AbstractCreateFeature {

	public FinalStateCreateFeature(IFeatureProvider fp) {
		super(fp, "Final State", "Creates a new final state");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		Object bo = getBusinessObjectForPictogramElement(context.getTargetContainer().getLink().getPictogramElement());
		return bo instanceof Statemachine;
	}

	@Override
	public Object[] create(ICreateContext context) {
		FinalState finalState = StatemachineFactory.eINSTANCE.createFinalState();
		finalState.setName("Final State");
		Statemachine bo = (Statemachine) getBusinessObjectForPictogramElement(context.getTargetContainer().getLink().getPictogramElement());
		bo.getStates().add(finalState);
		addGraphicalRepresentation(context, finalState);
		getFeatureProvider().getDirectEditingInfo().setActive(true);
		return new Object[] { finalState };
	}

}
