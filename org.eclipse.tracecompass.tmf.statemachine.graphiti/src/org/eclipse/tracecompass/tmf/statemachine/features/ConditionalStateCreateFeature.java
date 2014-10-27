package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import statemachine.ConditionalState;
import statemachine.StatemachineFactory;

public class ConditionalStateCreateFeature extends AbstractCreateFeature {

	public ConditionalStateCreateFeature(IFeatureProvider fp) {
		super(fp, "Condition", "Creates a new condition");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		ConditionalState conditionalState = StatemachineFactory.eINSTANCE.createConditionalState();
		conditionalState.setName("Condition");
		addGraphicalRepresentation(context, conditionalState);
		getFeatureProvider().getDirectEditingInfo().setActive(true);
		return new Object[] { conditionalState };
	}

}
