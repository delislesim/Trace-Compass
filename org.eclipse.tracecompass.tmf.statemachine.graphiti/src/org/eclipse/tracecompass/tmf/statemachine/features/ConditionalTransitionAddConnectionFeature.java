package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;

import statemachine.AbstractTransition;
import statemachine.ConditionalTransition;

public class ConditionalTransitionAddConnectionFeature extends AbstractAddFeature {

	public ConditionalTransitionAddConnectionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		final Object newObject = context.getNewObject();
		if(newObject instanceof AbstractTransition){
			if(context instanceof IAddConnectionContext){
				return true;
			}
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		IAddConnectionContext addConContext = (IAddConnectionContext) context;
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();

		Connection connection = peCreateService.createFreeFormConnection(getDiagram());
		connection.setStart(addConContext.getSourceAnchor());
		connection.setEnd(addConContext.getTargetAnchor());

		Polyline polyline = gaService.createPlainPolyline(connection);
		polyline.setForeground(manageColor(IColorConstant.BLACK));

		ConditionalTransition addedTransition = (ConditionalTransition) context.getNewObject();
		if(addedTransition.eResource() == null) {
			getDiagram().eResource().getContents().add(addedTransition);
		}		
		link(connection, addedTransition);
		
		ConnectionDecorator nameDecorator = peCreateService.createConnectionDecorator(connection, true, 0.5, true);
		Text name = gaService.createDefaultText(getDiagram(), nameDecorator);
		gaService.setLocation(name, 10, 0);
		name.setValue(addedTransition.getName());
		
		ConnectionDecorator cd;
        cd = peCreateService.createConnectionDecorator(connection, false, 1.0, true);
        createArrow(cd);

		return connection;
	}
	
	private Polyline createArrow(GraphicsAlgorithmContainer gaContainer) {
		IGaService gaService = Graphiti.getGaService();
		Polyline arrow = gaService.createPolyline(gaContainer, new int[] {-15, 10, 0, 0, -15, -10});
		arrow.setForeground(manageColor(IColorConstant.BLACK));
		return arrow;
	}

}
