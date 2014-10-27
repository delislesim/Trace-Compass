package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import statemachine.InitialState;
import statemachine.StatemachineFactory;

public class InitialStateCreateFeature extends AbstractCreateFeature {

	public InitialStateCreateFeature(IFeatureProvider fp) {
		super(fp, "Initial State", "Creates a new initial state");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		InitialState initialState = StatemachineFactory.eINSTANCE.createInitialState();
		initialState.setName("Initial State");
		addGraphicalRepresentation(context, initialState);
		getDiagram().eResource().getContents().add(initialState);
		
		return new Object[] { initialState };
	}

}
