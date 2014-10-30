package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

import statemachine.AbstractState;
import statemachine.InitialState;
import statemachine.Statemachine;

public class InitialStateAddFeature extends AbstractAddFeature {
	
	private int initialStateSize = 50;

	public InitialStateAddFeature(IFeatureProvider fp) {
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
		
		InitialState addedInitialState = (InitialState) context.getNewObject();

		ContainerShape containerShape = peCreateService.createContainerShape(targetStateMachine, true);
		Ellipse ellipse = gaService.createEllipse(containerShape);
		//RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(containerShape, 5, 5);
		gaService.setLocationAndSize(ellipse, context.getX(), context.getY(), context.getWidth(), context.getHeight());
		ellipse.setFilled(true);
		ellipse.setHeight(initialStateSize);
		ellipse.setWidth(initialStateSize);
		
		/*Shape shape = peCreateService.createShape(containerShape, false);
		Text text = gaService.createText(shape, addedInitialState.getName());
		text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
		gaService.setLocationAndSize(text, 0, 0, context.getWidth(), context.getHeight());*/

		peCreateService.createChopboxAnchor(containerShape);
		
		if(addedInitialState.eResource() == null) {
			getDiagram().eResource().getContents().add(addedInitialState);
		}
		
		link(containerShape, addedInitialState);
		
		layoutPictogramElement(containerShape);

		return containerShape;
	}

}
