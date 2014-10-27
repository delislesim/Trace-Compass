package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import statemachine.AbstractTransition;

public class TransitionsUpdateFeature extends AbstractUpdateFeature {

	public TransitionsUpdateFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		return true;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		String connectionDecoratorName = null;
        PictogramElement pictogramElement = context.getPictogramElement();
        if(pictogramElement instanceof Connection) {
        	EList<ConnectionDecorator> connectionDecorators = ((Connection) pictogramElement).getConnectionDecorators();
        	for(ConnectionDecorator decorator : connectionDecorators) {
        		if(decorator.getGraphicsAlgorithm() instanceof Text) {
        			connectionDecoratorName = ((Text)decorator.getGraphicsAlgorithm()).getValue();
        		}
        	}
        }
        
        String transitionName = null;
        Object bo = getBusinessObjectForPictogramElement(pictogramElement);
        if(bo instanceof AbstractTransition) {
        	transitionName = ((AbstractTransition)bo).getName();
        }
        
		if ((connectionDecoratorName == null && transitionName != null)
				|| (connectionDecoratorName != null && !connectionDecoratorName.equals(transitionName))) {
			return Reason.createTrueReason("Name is out of date");
		} else {
			return Reason.createFalseReason();
		}
	}

	@Override
	public boolean update(IUpdateContext context) {
		String transitionName = null;
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
        if (bo instanceof AbstractTransition) {
        	transitionName = ((AbstractTransition)bo).getName();
        }
        
        if(pictogramElement instanceof Connection) {
        	EList<ConnectionDecorator> connectionDecorators = ((Connection) pictogramElement).getConnectionDecorators();
        	for(ConnectionDecorator decorator : connectionDecorators) {
        		if(decorator.getGraphicsAlgorithm() instanceof Text) {
        			((Text)decorator.getGraphicsAlgorithm()).setValue(transitionName);
        			return true;
        		}
        	}
        }
        
		return false;
	}

}
