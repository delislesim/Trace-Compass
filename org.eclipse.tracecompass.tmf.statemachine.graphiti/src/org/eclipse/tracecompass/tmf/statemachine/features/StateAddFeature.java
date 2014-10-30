package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

import statemachine.State;
import statemachine.Statemachine;

public class StateAddFeature extends AbstractAddFeature {
	
	static final int stateSize = 75;

	public StateAddFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		final Object newObject = context.getNewObject();
		if(newObject instanceof State){
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
		
		State addedState = (State) context.getNewObject();

		ContainerShape containerShape = peCreateService.createContainerShape(targetStateMachine, true);
		Ellipse ellipse = gaService.createEllipse(containerShape);
		gaService.setLocationAndSize(ellipse, context.getX(), context.getY(), context.getWidth(), context.getHeight());
		ellipse.setFilled(false);
		ellipse.setHeight(stateSize);
		ellipse.setWidth(stateSize);
		
		Shape shape = peCreateService.createShape(containerShape, false);
		Text text = gaService.createText(shape, addedState.getName());
		text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
		gaService.setLocationAndSize(text, 0, 0, context.getWidth(), context.getHeight());

		peCreateService.createChopboxAnchor(containerShape);
		
		if(addedState.eResource() == null) {
			getDiagram().eResource().getContents().add(addedState);
		}
		
		link(containerShape, addedState);
		
		IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
		directEditingInfo.setMainPictogramElement(containerShape);
		directEditingInfo.setPictogramElement(shape);
		directEditingInfo.setGraphicsAlgorithm(text);
		
		layoutPictogramElement(containerShape);

		return containerShape;
	}

}
