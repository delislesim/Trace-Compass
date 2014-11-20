package org.eclipse.tracecompass.tmf.statemachine.features;

import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;

import statemachine.Statemachine;

public class StatemachineAddFeature extends AbstractAddFeature {

	public StatemachineAddFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		final Object newObject = context.getNewObject();
		if(newObject instanceof Statemachine){
			if(context.getTargetContainer() instanceof Diagram){
				return true;
			}
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		Diagram targetDiagram = (Diagram) context.getTargetContainer();
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();
		
		Statemachine addedStatemachine = (Statemachine) context.getNewObject();

		ContainerShape containerShape = peCreateService.createContainerShape(targetDiagram, true);
		
		int width = 800;
		int height = 500;
		int headerHeight = (int) (0.1 * height);
		
		RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(containerShape, 5, 5);
		roundedRectangle.setForeground(manageColor(IColorConstant.BLACK));
		roundedRectangle.setBackground(manageColor(IColorConstant.WHITE));
		roundedRectangle.setTransparency(0.7);
		roundedRectangle.setLineWidth(2);
		gaService.setLocationAndSize(roundedRectangle, context.getX(), context.getY(), width, height);
		
		if(addedStatemachine.eResource() == null) {
			getDiagram().eResource().getContents().add(addedStatemachine);
		}
		
		link(containerShape, addedStatemachine);
		
		Shape line = peCreateService.createShape(containerShape, false);
		Polyline polyline = gaService.createPolyline(line, new int [] {0, headerHeight, width, headerHeight});
		polyline.setForeground(manageColor(IColorConstant.BLACK));
		polyline.setLineWidth(2);
		
		Shape shape = peCreateService.createShape(containerShape, false);
		Text text = gaService.createText(shape, addedStatemachine.getName());
		text.setForeground(manageColor(IColorConstant.BLACK));
		text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		gaService.setLocationAndSize(text, 0, 0, width, headerHeight);
		
		link(shape, addedStatemachine);
		
		IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
		directEditingInfo.setMainPictogramElement(containerShape);
		directEditingInfo.setPictogramElement(shape);
		directEditingInfo.setGraphicsAlgorithm(text);
		
		layoutPictogramElement(containerShape);
		
		return containerShape;
	}

}
