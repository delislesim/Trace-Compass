package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import statemachine.AbstractState;
import statemachine.ConditionalTransition;
import statemachine.StatemachineFactory;

public class ConditionalTransitionCreateConnectionFeature extends AbstractCreateConnectionFeature {
	
	private AbstractState targetState = null;
	private AbstractState sourceState = null;

	public ConditionalTransitionCreateConnectionFeature(IFeatureProvider fp) {
		super(fp, "Conditional Transition", "Creates a new conditional transition between two states");
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
		
		return sourcePictogramElement != null && targetPictogramElement != null;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		
		final ConditionalTransition transition = StatemachineFactory.eINSTANCE.createConditionalTransition();
		transition.setName("Conditional Transition");
		if (targetState != null) {
			transition.setState(targetState);
		}
		
		if(sourceState != null) {
			//TODO: Ajouter la transition au tableau du state source
//			TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(sourceState);
//
//			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
//
//				@Override
//				protected void doExecute() {
//					sourceState.getTransitions().add(transition);
//				}
//			});
		}
		
		AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		addContext.setNewObject(transition);
		newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
		
		getDiagram().eResource().getContents().add(transition);

		return newConnection;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		return getBusinessObjectForPictogramElement(context.getSourcePictogramElement()) instanceof AbstractState;
	}

}
