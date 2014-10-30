package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import statemachine.InitialState;
import statemachine.Statemachine;
import statemachine.StatemachineFactory;

public class InitialStateCreateFeature extends AbstractCreateFeature {

	public InitialStateCreateFeature(IFeatureProvider fp) {
		super(fp, "Initial State", "Creates a new initial state");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		Object bo = getBusinessObjectForPictogramElement(context.getTargetContainer().getLink().getPictogramElement());
		return bo instanceof Statemachine;
	}

	@Override
	public Object[] create(ICreateContext context) {
		InitialState initialState = StatemachineFactory.eINSTANCE.createInitialState();
		initialState.setName("Initial State");
		Statemachine bo = (Statemachine) getBusinessObjectForPictogramElement(context.getTargetContainer().getLink().getPictogramElement());
		bo.getStates().add(initialState);
		addGraphicalRepresentation(context, initialState);
		
		return new Object[] { initialState };
	}

}
