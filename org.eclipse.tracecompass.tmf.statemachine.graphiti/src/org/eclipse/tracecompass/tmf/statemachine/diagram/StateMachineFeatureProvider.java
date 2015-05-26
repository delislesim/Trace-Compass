package org.eclipse.tracecompass.tmf.statemachine.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;
import org.eclipse.tracecompass.tmf.statemachine.features.ConditionalStateAddFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.ConditionalStateCreateFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.ConditionalStateLayoutFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.FinalStateAddFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.FinalStateCreateFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.InitialStateAddFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.InitialStateCreateFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.StateAddFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.StateCreateFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.StatemachineAddFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.StatemachineCreateFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.StatemachineLayoutFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.StatemachineUpdateFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.StatesDirectEditFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.StatesLayoutFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.StatesUpdateFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.TransitionAddConnectionFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.TransitionCreateConnectionFeature;
import org.eclipse.tracecompass.tmf.statemachine.features.TransitionsUpdateFeature;

import statemachine.AbstractState;
import statemachine.AbstractTransition;
import statemachine.ConditionalState;
import statemachine.FinalState;
import statemachine.InitialState;
import statemachine.State;
import statemachine.Statemachine;
import statemachine.Transition;


public class StateMachineFeatureProvider extends DefaultFeatureProvider {

	public StateMachineFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] {new StateCreateFeature(this), new InitialStateCreateFeature(this), /*new FinalStateCreateFeature(this),*/ new ConditionalStateCreateFeature(this), new StatemachineCreateFeature(this)};
	}
	
	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] {new TransitionCreateConnectionFeature(this)};
	}
	
	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		if (context instanceof IAddConnectionContext) {
			if (context.getNewObject() instanceof Transition) {
				return new TransitionAddConnectionFeature(this);
			}
		} else if (context instanceof IAddContext) {
			if (context.getNewObject() instanceof State) {
				return new StateAddFeature(this);
			} else if (context.getNewObject() instanceof InitialState) {
				return new InitialStateAddFeature(this);
			/*} else if (context.getNewObject() instanceof FinalState) {
				return new FinalStateAddFeature(this);*/
			} else if (context.getNewObject() instanceof ConditionalState) {
				return new ConditionalStateAddFeature(this);
			} else if (context.getNewObject() instanceof Statemachine) {
				return new StatemachineAddFeature(this);
			}
		}

		return super.getAddFeature(context);
	}
	
	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		if (context.getPictogramElement() instanceof ContainerShape) {
			if (getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof State || getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof FinalState) {
				return new StatesLayoutFeature(this);
			} else if (getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof ConditionalState) {
				return new ConditionalStateLayoutFeature(this);
			} else if (getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof Statemachine) {
				return new StatemachineLayoutFeature(this);
			}
		}
	
		return super.getLayoutFeature(context);
	}
	
	@Override
	public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
	    PictogramElement pe = context.getPictogramElement();
	    Object bo = getBusinessObjectForPictogramElement(pe);
	    if (bo instanceof AbstractState) {
	        return new StatesDirectEditFeature(this);
	    }
	    return super.getDirectEditingFeature(context);
	}
	
	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape || pictogramElement instanceof Connection) {
			Object bo = getBusinessObjectForPictogramElement(pictogramElement);
			if (bo instanceof State || bo instanceof FinalState) {
				return new StatesUpdateFeature(this);
			} else if (bo instanceof AbstractTransition) {
				return new TransitionsUpdateFeature(this);
			} else if (bo instanceof Statemachine) {
				return new StatemachineUpdateFeature(this);
			}
		}
		return super.getUpdateFeature(context);
	}
}
