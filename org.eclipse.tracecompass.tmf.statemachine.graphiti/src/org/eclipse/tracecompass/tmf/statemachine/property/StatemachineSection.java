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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import statemachine.AbstractState;
import statemachine.AbstractTransition;

public class StatemachineSection extends GFPropertySection implements ITabbedPropertyConstants {
	private Text nameText;
	private Group stateChangeGroup;
	 
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
 
        TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
        final Composite composite = factory.createFlatFormComposite(parent);
        FormData data;
 
        nameText = factory.createText(composite, "");
        data = new FormData();
        data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, VSPACE);
        nameText.setLayoutData(data);
        
        Label label = factory.createLabel(composite, "Name");
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(nameText, -HSPACE);
        data.top = new FormAttachment(nameText, 0, SWT.CENTER);
        label.setLayoutData(data);
        
        //createStateChange(composite);
        stateChangeGroup = factory.createGroup(composite, "State Change");
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(label, 0, SWT.DEFAULT);
		stateChangeGroup.setLayoutData(data);
        Table stateChangeTable = factory.createTable(stateChangeGroup, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        stateChangeTable.setLocation(VSPACE, 4*HSPACE);
        stateChangeTable.pack();
        Button addStateChangeButton = factory.createButton(stateChangeGroup, "Add state change", SWT.PUSH);
        addStateChangeButton.setLocation(stateChangeTable.getSize().x + HSPACE, 4*HSPACE);
        addStateChangeButton.pack();
        Button removeStateChangeButton = factory.createButton(stateChangeGroup, "Remove state change", SWT.PUSH);
        removeStateChangeButton.setLocation(stateChangeTable.getSize().x + HSPACE, addStateChangeButton.getLocation().y + addStateChangeButton.getSize().y + VSPACE);
        removeStateChangeButton.pack();
        Button editStateChangeButton = factory.createButton(stateChangeGroup, "Edit state change", SWT.PUSH);
        editStateChangeButton.setLocation(stateChangeTable.getSize().x + HSPACE, removeStateChangeButton.getLocation().y + removeStateChangeButton.getSize().y + VSPACE);
        editStateChangeButton.pack();
        
        addStateChangeButton.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			addStateChangeDialog(composite.getDisplay());
    		}
		});    	
 
        /*CLabel valueLabel = factory.createCLabel(composite, "Name:");
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(nameText, -HSPACE);
        data.top = new FormAttachment(nameText, 0, SWT.CENTER);
        valueLabel.setLayoutData(data);*/
        
        nameText.addModifyListener(new ModifyListener() {

        	@Override
        	public void modifyText(ModifyEvent e) {
        		String value = nameText.getText();
        		if (value == null) {
        			value = "";
        		}
        		PictogramElement pe = getSelectedPictogramElement();
        		if (pe != null) {
        			Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
        			if (bo == null)
        				return;
        			String name = null;
        			if(bo instanceof AbstractState) {
        				name = ((AbstractState) bo).getName();
        			} else if (bo instanceof AbstractTransition) {
        				name = ((AbstractTransition) bo).getName();
        			}
        			if (value.equals(name))
        				return;
        		}
        		final String typedValue = value;
        		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
        				
        			@Override
        			public void execute(IContext context) {
        				PictogramElement pe = getSelectedPictogramElement();
        				if (pe != null) {
        					Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
        					if (bo == null)
        						return;
                			if(bo instanceof AbstractState) {
                				((AbstractState) bo).setName(typedValue);
                			} else if (bo instanceof AbstractTransition) {
                				((AbstractTransition) bo).setName(typedValue);
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
        		stateChangeGroup.setVisible(false);
        		if(stateName != null) {
        			nameText.setText(stateName);
        		} else {
        			nameText.setText("");
        		}
        	} else if (bo instanceof AbstractTransition) {
        		String transitionName = ((AbstractTransition)bo).getName();
        		stateChangeGroup.setVisible(true);			
        		if(transitionName != null) {
        			nameText.setText(transitionName);
        		} else {
        			nameText.setText("");
        		}
        	}
        }
    }
    
    private void addStateChangeDialog(Display display) {
        Shell dialog = new Shell(display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setLayout (new RowLayout ());
        Button addButton = new Button(dialog, SWT.PUSH);
        addButton.setText("Add");
        Button cancelButton = new Button(dialog, SWT.PUSH);
        cancelButton.setText("Cancel");
        
        dialog.pack();
        dialog.open();
		while (!dialog.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
    }
}
