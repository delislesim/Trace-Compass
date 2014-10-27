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

import statemachine.AbstractState;

public class StateSection extends GFPropertySection implements ITabbedPropertyConstants {
	private Text stateNameText;
	
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        
        TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
        final Composite composite = factory.createFlatFormComposite(parent);
        composite.setLayout(new GridLayout(2, false));
        
        GridData gridData;
        
        Label label = factory.createLabel(composite, "Name");
        
        stateNameText = factory.createText(composite, "");
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        stateNameText.setLayoutData(gridData);
        
        stateNameText.addModifyListener(new ModifyListener() {

        	@Override
        	public void modifyText(ModifyEvent e) {
        		String newStateName = stateNameText.getText();
        		if (newStateName == null) {
        			newStateName = "";
        		}
        		PictogramElement pe = getSelectedPictogramElement();
        		if (pe != null) {
        			Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
        			if (bo == null)
        				return;
        			String ActualStateName = null;
        			if(bo instanceof AbstractState) {
        				ActualStateName = ((AbstractState) bo).getName();
        			}
        			if (newStateName.equals(ActualStateName))
        				return;
        		}
        		final String stateName = newStateName;
        		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
        				
        			@Override
        			public void execute(IContext context) {
        				PictogramElement pe = getSelectedPictogramElement();
        				if (pe != null) {
        					Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
        					if (bo == null)
        						return;
                			if(bo instanceof AbstractState) {
                				((AbstractState) bo).setName(stateName);
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
        	
        	if(bo instanceof AbstractState) {
        		String stateName = ((AbstractState)bo).getName();
        		stateNameText.setText((stateName != null) ? stateName : "");
        	}
        }
    }
}
