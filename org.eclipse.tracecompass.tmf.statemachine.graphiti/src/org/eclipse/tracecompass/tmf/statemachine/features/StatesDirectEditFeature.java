package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import statemachine.AbstractState;

public class StatesDirectEditFeature extends AbstractDirectEditingFeature {

	public StatesDirectEditFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public int getEditingType() {
		return TYPE_TEXT;
	}
	
	@Override
	public boolean canDirectEdit(IDirectEditingContext context) {
		PictogramElement pe = context.getPictogramElement();
        Object bo = getBusinessObjectForPictogramElement(pe);
        GraphicsAlgorithm ga = context.getGraphicsAlgorithm();
        return true;
	}

	@Override
	public String getInitialValue(IDirectEditingContext context) {
		PictogramElement pictogram = context.getPictogramElement();
		AbstractState state = (AbstractState) getBusinessObjectForPictogramElement(pictogram);
		return state.getName();
	}
	
	@Override
	public String checkValueValid(String value, IDirectEditingContext context) {
		if(value.length() < 1){
			return "Please enter any state name";
		}
		return null;
	}
	
	@Override
	public void setValue(String value, IDirectEditingContext context) {
		PictogramElement pe = context.getPictogramElement();
        AbstractState abstractState = (AbstractState) getBusinessObjectForPictogramElement(pe);
        abstractState.setName(value);
        updatePictogramElement(((Shape) pe).getContainer());
	}
}
