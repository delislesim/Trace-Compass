package org.eclipse.tracecompass.tmf.statemachine.property;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

import statemachine.Statemachine;

public class StatemachineFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pictogramElement) {
		EObject eObject =
	            Graphiti.getLinkService()
	            .getBusinessObjectForLinkedPictogramElement(pictogramElement);
	        if (eObject instanceof Statemachine) {
	            return true;
	        }
	        return false;
	}

}
