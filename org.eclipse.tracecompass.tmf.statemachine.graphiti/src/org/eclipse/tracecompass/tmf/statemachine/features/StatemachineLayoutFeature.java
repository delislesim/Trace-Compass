package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

import statemachine.AbstractState;
import statemachine.Statemachine;
import statemachine.Transition;

public class StatemachineLayoutFeature extends AbstractLayoutFeature {

	public StatemachineLayoutFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		return context.getPictogramElement() instanceof ContainerShape
				&& getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof Statemachine;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
		
		int containerWidth = containerGa.getWidth();
		
		boolean changeNeeded = false;
		
		for(Shape shape : containerShape.getChildren()) {
			GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
			IGaService gaService = Graphiti.getGaService();
			IDimension size = gaService.calculateSize(graphicsAlgorithm);
			if(shape.getLink() != null && (getBusinessObjectForPictogramElement(shape.getLink().getPictogramElement()) instanceof AbstractState || getBusinessObjectForPictogramElement(shape.getLink().getPictogramElement()) instanceof Transition)) {
				continue;
			}
			if(containerWidth != size.getWidth()) {
				if (graphicsAlgorithm instanceof Polyline) {
					Polyline polyline = (Polyline) graphicsAlgorithm;
					Point secondPoint = polyline.getPoints().get(1);
					Point newSecondPoint = gaService.createPoint(containerWidth, secondPoint.getY()); 
					polyline.getPoints().set(1, newSecondPoint);
					changeNeeded = true;
				} else {
					gaService.setWidth(graphicsAlgorithm, containerWidth);
					changeNeeded = true;
				}
			}
		}
		
		return changeNeeded;
	}

}
