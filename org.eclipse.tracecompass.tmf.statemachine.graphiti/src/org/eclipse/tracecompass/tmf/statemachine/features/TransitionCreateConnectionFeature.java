package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AbstractAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeTree;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeTreePath;
import org.eclipse.tracecompass.tmf.attributetree.core.utils.AttributeTreeUtils;

import statemachine.AbstractState;
import statemachine.ConditionalState;
import statemachine.FinalState;
import statemachine.StateChange;
import statemachine.StateValue;
import statemachine.StateValueType;
import statemachine.Statemachine;
import statemachine.StatemachineFactory;
import statemachine.Transition;

public class TransitionCreateConnectionFeature extends AbstractCreateConnectionFeature {
	
	private AbstractState targetState = null;
	private AbstractState sourceState = null;

	public TransitionCreateConnectionFeature(IFeatureProvider fp) {
		super(fp, "Transition", "Creates a new transition between two states");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		PictogramElement sourcePictogramElement = context.getSourcePictogramElement();
		PictogramElement targetPictogramElement = context.getTargetPictogramElement();

		if (getBusinessObjectForPictogramElement(sourcePictogramElement) instanceof AbstractState && getBusinessObjectForPictogramElement(targetPictogramElement) instanceof AbstractState) {
			targetState = (AbstractState) getBusinessObjectForPictogramElement(targetPictogramElement);
			sourceState = (AbstractState) getBusinessObjectForPictogramElement(sourcePictogramElement);
			return true;
		}
		
		return sourceState != null && targetState != null && sourcePictogramElement != null && targetPictogramElement != null;
		//return sourcePictogramElement != null && targetPictogramElement != null;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		
		final Transition transition = StatemachineFactory.eINSTANCE.createTransition();
		if(sourceState instanceof ConditionalState && sourceState.getTransitions().isEmpty()) {
			transition.setName("then");
		} else if (sourceState instanceof ConditionalState && !sourceState.getTransitions().isEmpty()){
			transition.setName("else");
		} else {
			transition.setName("Transition");
		}
		
		if (targetState != null) {
			transition.setState(targetState);
			if (!(targetState instanceof ConditionalState)) {
				StateChange stateChange = getAppropriateStateChange();
				transition.getStateChange().add(stateChange);
			}
		}
		
		if(sourceState != null) {
			sourceState.getTransitions().add(transition);
		}
		
		AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		addContext.setNewObject(transition);
		newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);

		return newConnection;
	}

	private StateChange getAppropriateStateChange() {
		String attributePath = null;
		if(targetState.eContainer() instanceof Statemachine) {
			Statemachine stateMachine = (Statemachine)targetState.eContainer();
			attributePath = stateMachine.getAssociatedAttribute();
		}
		
		StateChange stateChange = StatemachineFactory.eINSTANCE.createStateChange();
		AbstractAttributeNode leafNode = AttributeTree.getInstance().getNodeFromPath(AttributeTreeUtils.getAttributeTreeFile(getDiagram().getName()), attributePath);
		AttributeTreePath attributeTreePath = new AttributeTreePath(leafNode);
		stateChange.getStateAttribute().addAll(attributeTreePath.getAllStateAttribute());
		
		StateValue stateValue = StatemachineFactory.eINSTANCE.createStateValue();
		if (targetState instanceof FinalState) {
			stateValue.setValue("");
			stateValue.setType(StateValueType.NULL);
		} else {
			stateValue.setValue(targetState.getName());
			stateValue.setType(StateValueType.DEFINED_STATE);
		}
		stateChange.setStateValue(stateValue);
		
		return stateChange;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		boolean canStart = false;
		
		canStart = getBusinessObjectForPictogramElement(context.getSourcePictogramElement()) instanceof AbstractState;
		AbstractState startState = null;
		if(canStart) {
			startState = (AbstractState) getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
		}
		
		if(startState instanceof ConditionalState && startState.getTransitions().size() >= 2) {
			canStart = false;
		}
		return canStart;
	}

}
