package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import statemachine.State;
import statemachine.Statemachine;
import statemachine.StatemachineFactory;

public class StateCreateFeature extends AbstractCreateFeature {

	public StateCreateFeature(IFeatureProvider fp) {
		super(fp, "State", "Creates a new state");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		Object bo = getBusinessObjectForPictogramElement(context.getTargetContainer().getLink().getPictogramElement());
		return bo instanceof Statemachine;
	}

	@Override
	public Object[] create(ICreateContext context) {
		State state = StatemachineFactory.eINSTANCE.createState();
		state.setName("State");
		Statemachine bo = (Statemachine) getBusinessObjectForPictogramElement(context.getTargetContainer().getLink().getPictogramElement());
		bo.getStates().add(state);
		addGraphicalRepresentation(context, state);
		getFeatureProvider().getDirectEditingInfo().setActive(true);
		return new Object[] { state };
	}

}
