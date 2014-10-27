package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import statemachine.State;
import statemachine.StatemachineFactory;

public class StateCreateFeature extends AbstractCreateFeature {

	public StateCreateFeature(IFeatureProvider fp) {
		super(fp, "State", "Creates a new state");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		State state = StatemachineFactory.eINSTANCE.createState();
		state.setName("State");
		addGraphicalRepresentation(context, state);
		getFeatureProvider().getDirectEditingInfo().setActive(true);
		return new Object[] { state };
	}

}
