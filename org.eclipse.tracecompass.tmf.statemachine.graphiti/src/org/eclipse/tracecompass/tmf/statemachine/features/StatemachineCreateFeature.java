package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import statemachine.Statemachine;
import statemachine.StatemachineFactory;

public class StatemachineCreateFeature extends AbstractCreateFeature {

	public StatemachineCreateFeature(IFeatureProvider fp) {
		super(fp, "State machine", "Creates a new state machine");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		Statemachine statemachine = StatemachineFactory.eINSTANCE.createStatemachine();
		statemachine.setName("State machine");
		addGraphicalRepresentation(context, statemachine);
		getFeatureProvider().getDirectEditingInfo().setActive(true);
		return new Object[] { statemachine };
	}

}
