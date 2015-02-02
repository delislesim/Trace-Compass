package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;

import statemachine.State;
import statemachine.Statemachine;
import statemachine.StatemachineFactory;

public class StateCreateFeature extends AbstractCreateFeature {

	public StateCreateFeature(IFeatureProvider fp) {
		super(fp, "State", "Creates a new state");
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
		State state = StatemachineFactory.eINSTANCE.createState();
		state.setName("State");
		Statemachine bo = (Statemachine) getBusinessObjectForPictogramElement(context.getTargetContainer().getLink().getPictogramElement());
		bo.getStates().add(state);
		addGraphicalRepresentation(context, state);
		getFeatureProvider().getDirectEditingInfo().setActive(true);
		return new Object[] { state };
	}

}
