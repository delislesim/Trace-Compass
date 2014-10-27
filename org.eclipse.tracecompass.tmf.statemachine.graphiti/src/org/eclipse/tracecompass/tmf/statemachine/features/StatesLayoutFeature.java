package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

import statemachine.AbstractState;


public class StatesLayoutFeature extends AbstractLayoutFeature implements
		ILayoutFeature {

	public StatesLayoutFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		return context.getPictogramElement() instanceof ContainerShape
				&& getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof AbstractState;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape) {
			ContainerShape containerShape = (ContainerShape) pictogramElement;
			GraphicsAlgorithm outerGraphicsAlgorithm = containerShape.getGraphicsAlgorithm();
			if (outerGraphicsAlgorithm instanceof Ellipse) {
				Ellipse ellipse = (Ellipse) outerGraphicsAlgorithm;
				EList<Shape> children = containerShape.getChildren();
				if (children.size() > 0) {
					Shape shape = children.get(0);
					GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
					if (graphicsAlgorithm instanceof Text) {
						Graphiti.getGaLayoutService().setLocationAndSize(graphicsAlgorithm, 0, 0, ellipse.getWidth(), ellipse.getHeight());
						return true;
					}
				}
			}
		}
		return false;
	}
}
