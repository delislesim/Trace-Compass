package org.eclipse.tracecompass.tmf.statemachine.features;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import statemachine.ConditionalState;

public class ConditionalStateLayoutFeature extends AbstractLayoutFeature {

	public ConditionalStateLayoutFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		return context.getPictogramElement() instanceof ContainerShape
				&& getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof ConditionalState;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Polygon polygon = (Polygon) pictogramElement.getGraphicsAlgorithm();
		GraphicsAlgorithm graphicsAlgorithm = pictogramElement.getGraphicsAlgorithm();
		float height = graphicsAlgorithm.getHeight();
		float width = graphicsAlgorithm.getWidth();
		if (graphicsAlgorithm instanceof Polygon) {
			float maxX = 0;
			float maxY = 0;
			EList<Point> points = polygon.getPoints();
			for (Iterator<Point> iterator = points.iterator(); iterator.hasNext();) {
				Point point = iterator.next();
				if (point.getX() > maxX) {
					maxX = point.getX();
				}
				if (point.getY() > maxY) {
					maxY = point.getY();
				}
			}
			float scaleX = width / maxX;
			float scaleY = height / maxY;
			if (scaleX != 1 || scaleY != 1) {
				for (Iterator<Point> iterator = points.iterator(); iterator.hasNext();) {
					Point point = iterator.next();
					int newX = Math.round(point.getX() * scaleX);
					point.setX(newX);
					int newY = Math.round(point.getY() * scaleY);
					point.setY(newY);
				}
				return true;
			}
		}

		return false;
	}

}
