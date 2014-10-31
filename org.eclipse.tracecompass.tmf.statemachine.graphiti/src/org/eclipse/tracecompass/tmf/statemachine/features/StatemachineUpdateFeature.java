package org.eclipse.tracecompass.tmf.statemachine.features;

import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import statemachine.Statemachine;

public class StatemachineUpdateFeature extends AbstractUpdateFeature {

	public StatemachineUpdateFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		return true;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		String statemachineName = null;
		PictogramElement pictogramElement = context.getPictogramElement();
		if(pictogramElement instanceof ContainerShape) {
			List<Shape> shapeList = ((ContainerShape) pictogramElement).getChildren();
			for(Shape shape : shapeList) {
				if(shape.getGraphicsAlgorithm() instanceof Text) {
					statemachineName = ((Text)shape.getGraphicsAlgorithm()).getValue();
				}
			}
		}
		
		String boStatemachineName = null;
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if(bo instanceof Statemachine) {
			boStatemachineName = ((Statemachine)bo).getName();
		}
		
		if((statemachineName == null && boStatemachineName != null) || (statemachineName != null && !statemachineName.equals(boStatemachineName))) {
			return Reason.createTrueReason("Name is out of date");
		} else {
			return Reason.createFalseReason();
		}
	}

	@Override
	public boolean update(IUpdateContext context) {
		String statemachinName = null;
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if(bo instanceof Statemachine) {
			statemachinName = ((Statemachine)bo).getName();
		}
		
		if(pictogramElement instanceof ContainerShape) {
			List<Shape> shapeList = ((ContainerShape) pictogramElement).getChildren();
			for(Shape shape : shapeList) {
				if(shape.getGraphicsAlgorithm() instanceof Text) {
					((Text)shape.getGraphicsAlgorithm()).setValue(statemachinName);
					return true;
				}
			}
		}
		return false;
	}

}
