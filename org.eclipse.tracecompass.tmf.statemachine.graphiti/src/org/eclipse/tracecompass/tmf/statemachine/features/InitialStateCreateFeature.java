package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;

import statemachine.InitialState;
import statemachine.Statemachine;
import statemachine.StatemachineFactory;

public class InitialStateCreateFeature extends AbstractCreateFeature {

	public InitialStateCreateFeature(IFeatureProvider fp) {
		super(fp, "Initial State", "Creates a new initial state");
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
		InitialState initialState = StatemachineFactory.eINSTANCE.createInitialState();
		initialState.setName("Initial State");
		Statemachine bo = (Statemachine) getBusinessObjectForPictogramElement(context.getTargetContainer().getLink().getPictogramElement());
		bo.getStates().add(initialState);
		addGraphicalRepresentation(context, initialState);
		
		return new Object[] { initialState };
	}

}
