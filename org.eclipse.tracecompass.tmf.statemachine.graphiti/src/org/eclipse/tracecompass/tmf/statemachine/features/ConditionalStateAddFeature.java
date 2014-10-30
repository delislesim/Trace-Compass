package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

import statemachine.AbstractState;
import statemachine.ConditionalState;
import statemachine.Statemachine;

public class ConditionalStateAddFeature extends AbstractAddFeature {
	
	private int conditionSize = 50;

	public ConditionalStateAddFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		final Object newObject = context.getNewObject();
		if(newObject instanceof AbstractState){
			Object bo = getBusinessObjectForPictogramElement(context.getTargetContainer().getLink().getPictogramElement());
			return bo instanceof Statemachine;
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		ContainerShape targetStateMachine = context.getTargetContainer();
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();
		
		ConditionalState addedCondition = (ConditionalState) context.getNewObject();

		ContainerShape containerShape = peCreateService.createContainerShape(targetStateMachine, true);
		// top, middle-right, bottom, middle-left
		int xy[] = new int[] { 50, 0, 100, 50, 50, 100, 0, 50 };
		Polygon diamond = gaService.createPlainPolygon(containerShape, xy);
		gaService.setLocationAndSize(diamond, context.getX(), context.getY(), context.getWidth(), context.getHeight());
		diamond.setFilled(false);
		diamond.setHeight(conditionSize);
		diamond.setWidth(conditionSize);
		
		/*Shape shape = peCreateService.createShape(containerShape, false);
		Text text = gaService.createText(shape, addedState.getName());
		text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
		gaService.setLocationAndSize(text, 0, 0, context.getWidth(), context.getHeight());*/

		peCreateService.createChopboxAnchor(containerShape);
		
		if(addedCondition.eResource() == null) {
			getDiagram().eResource().getContents().add(addedCondition);
		}
		
		link(containerShape, addedCondition);
		
		/*IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
		directEditingInfo.setMainPictogramElement(containerShape);
		directEditingInfo.setPictogramElement(shape);
		directEditingInfo.setGraphicsAlgorithm(text);*/
		
		layoutPictogramElement(containerShape);

		return containerShape;
	}

}
