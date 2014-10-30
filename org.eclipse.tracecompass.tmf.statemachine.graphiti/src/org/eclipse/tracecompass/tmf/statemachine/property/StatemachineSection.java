package org.eclipse.tracecompass.tmf.statemachine.property;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import statemachine.Statemachine;

public class StatemachineSection extends GFPropertySection implements ITabbedPropertyConstants {
	
	private Text statemachineNameText;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
		final Composite composite = factory.createFlatFormComposite(parent);
		composite.setLayout(new GridLayout(2, false));

		GridData gridData;

		Label nameLabel = factory.createLabel(composite, "Name");

		statemachineNameText = factory.createText(composite, "");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		statemachineNameText.setLayoutData(gridData);
		
		statemachineNameText.addModifyListener(new ModifyListener() {

        	@Override
        	public void modifyText(ModifyEvent e) {
        		String newStatemachineName = statemachineNameText.getText();
        		PictogramElement pe = getSelectedPictogramElement();
        		if (pe != null) {
        			Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
        			if (bo == null)
        				return;
        			String actualStatemachineName = null;
        			if(bo instanceof Statemachine) {
        				actualStatemachineName = ((Statemachine) bo).getName();
        			}
        			if (newStatemachineName.equals(actualStatemachineName)) {
        				return;
        			}
        		}
        		final String statemachineName = newStatemachineName;
        		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
    				
        			@Override
        			public void execute(IContext context) {
        				PictogramElement pe = getSelectedPictogramElement();
        				if (pe != null) {
        					Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
        					if (bo == null)
        						return;
                			if(bo instanceof Statemachine) {
                				((Statemachine) bo).setName(statemachineName);
                			}        					
        				}
        			}
        			
        			@Override
        			public boolean canExecute(IContext context) {
        				return true;
        			}
        		};
        		CustomContext context = new CustomContext();
        		execute(feature, context);
        	}
		});
	}

	@Override
	public void refresh() {
		PictogramElement pe = getSelectedPictogramElement();
        if (pe != null) {
        	Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
        	if (bo == null)
                return;
        	
        	if(bo instanceof Statemachine) {
        		String statemachineName = ((Statemachine)bo).getName();
        		statemachineNameText.setText((statemachineName != null) ? statemachineName : "");
        	}
        }
	}
}
