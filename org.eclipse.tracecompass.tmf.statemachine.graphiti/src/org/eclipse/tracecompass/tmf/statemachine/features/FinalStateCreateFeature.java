package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import statemachine.FinalState;
import statemachine.StatemachineFactory;

public class FinalStateCreateFeature extends AbstractCreateFeature {

	public FinalStateCreateFeature(IFeatureProvider fp) {
		super(fp, "Final State", "Creates a new final state");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		FinalState finalState = StatemachineFactory.eINSTANCE.createFinalState();
		finalState.setName("Final State");
		addGraphicalRepresentation(context, finalState);
		getDiagram().eResource().getContents().add(finalState);
		
		return new Object[] { finalState };
	}

}
