package org.eclipse.tracecompass.tmf.statemachine.property;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AbstractAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeTreePath;
import org.eclipse.tracecompass.tmf.attributetree.core.utils.AttributeTreeUtils;
import org.eclipse.tracecompass.tmf.attributetree.ui.widgets.AttributeTreeComposite;
import org.eclipse.tracecompass.tmf.statemachine.util.ConvertStatemachineType;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import statemachine.AbstractTransition;
import statemachine.StateChange;
import statemachine.StateValue;
import statemachine.StateValueType;
import statemachine.StatemachineFactory;

public class TransitionSection extends GFPropertySection implements ITabbedPropertyConstants {
	private Text transitionNameText;
	private Table stateChangeTable;
	private Group stateChangeGroup;
	
	private ConvertStatemachineType util = new ConvertStatemachineType();
	
	private AttributeTreePath selectedPath;
	
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        
        TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
        final Composite composite = factory.createFlatFormComposite(parent);
        composite.setLayout(new GridLayout(2, false));
        
        GridData gridData;
        
        Label label = factory.createLabel(composite, "Name");
        
        transitionNameText = factory.createText(composite, "");
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        transitionNameText.setLayoutData(gridData);
        
        stateChangeGroup = factory.createGroup(composite, "State Change");
        stateChangeGroup.setLayout(new GridLayout(2, false));
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        stateChangeGroup.setLayoutData(gridData);
        
        stateChangeTable = factory.createTable(stateChangeGroup, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.FILL;
        gridData.verticalSpan = 2;
        stateChangeTable.setLayoutData(gridData);
        
        Button addStateChangeButton = factory.createButton(stateChangeGroup, "Add/Edit state change", SWT.PUSH);
        Button removeStateChangeButton = factory.createButton(stateChangeGroup, "Remove state change", SWT.PUSH);
        
        addStateChangeButton.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			int stateChangeTableIndex = stateChangeTable.getSelectionIndex();
    			StateChange stateChange = null;
    			if (stateChangeTableIndex >= 0) {
    				if(getTransition() != null) {
    					stateChange = getTransition().getStateChange().get(stateChangeTableIndex);
    				}
    			} else {
    				stateChange = StatemachineFactory.eINSTANCE.createStateChange();
    			}
    			
    			addEditStateChangeDialog(composite.getDisplay(), stateChange);
    		}
		});
        
        removeStateChangeButton.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			int stateChangeTableIndex = stateChangeTable.getSelectionIndex();
    			removeStateChange(stateChangeTableIndex);
    		}
        });
        
        transitionNameText.addModifyListener(new ModifyListener() {

        	@Override
        	public void modifyText(ModifyEvent e) {
        		String newTransitionName = transitionNameText.getText();
        		if (newTransitionName == null) {
        			newTransitionName = "";
        		}
        		String ActualTransitionName = null;
        		if(getTransition() != null) {
        			ActualTransitionName = getTransition().getName();
        		}
    			if (newTransitionName.equals(ActualTransitionName))
    				return;
        		final String transitionName = newTransitionName;
        		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
        				
        			@Override
        			public void execute(IContext context) {
        				if(getTransition() != null) {
        					getTransition().setName(transitionName);
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
		if(getTransition() != null) {
    		String TransitionName = getTransition().getName();
    		transitionNameText.setText((TransitionName != null) ? TransitionName : "");
    		stateChangeTable.removeAll();
    		for (int i = 0; i < getTransition().getStateChange().size(); i++) {
    			TableItem item = new TableItem(stateChangeTable, 0);
    			item.setText(getTransition().getStateChange().get(i).toString());
    		}
		}
    }
	
	private void addEditStateChangeDialog(Display display, final StateChange stateChange) {
		final Shell dialog = new Shell(display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setLayout (new GridLayout(2, false));
        dialog.setText("State change");
        
        GridData gridData;
        
        Group stateAttributeGroup = new Group(dialog, SWT.NONE);
        stateAttributeGroup.setText("State attribute");
        stateAttributeGroup.setLayout(new GridLayout(2, false));
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        stateAttributeGroup.setLayoutData(gridData);
        
        final AttributeTreeComposite attributeTree = new AttributeTreeComposite(stateAttributeGroup, SWT.NONE);
        attributeTree.setTreeViewerInput(AttributeTreeUtils.getAttributeTreeFile(getDiagram().getName()));
        attributeTree.getTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = attributeTree.getSelection();
				AbstractAttributeNode selectedNode = (AbstractAttributeNode)selection.getFirstElement();
				selectedPath = new AttributeTreePath(selectedNode);
			}
        	
        });
		
		// State value
        Group stateValueGroup = new Group(dialog, SWT.NONE);
        stateValueGroup.setText("State value");
        stateValueGroup.setLayout(new GridLayout(2, false));
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        stateValueGroup.setLayoutData(gridData);
        
		Label stateValueTypeLabel = new Label(stateValueGroup, SWT.NONE);
        stateValueTypeLabel.setText("Type");
        
        final Combo stateValueTypeCombo = new Combo(stateValueGroup, SWT.READ_ONLY);
        stateValueTypeCombo.setItems(util.getStateValueTypeString());
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        stateValueTypeCombo.setLayoutData(gridData);
        
        Label stateValueLabel = new Label(stateValueGroup, SWT.NONE);
        stateValueLabel.setText("Value");
        
        final Text stateValueText = new Text(stateValueGroup, SWT.SINGLE);
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        stateValueText.setLayoutData(gridData);
        
        stateValueTypeCombo.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		if(util.getStateValueTypeFromindex(stateValueTypeCombo.getSelectionIndex()).getName().equals(StateValueType.DEFINED_STATE.getName())) {
        			stateValueText.setEnabled(false);
        			if(getTransition() != null) {
        				stateValueText.setText(getTransition().getState().getName());
        			}
        		} else {
        			stateValueText.setEnabled(true);
        			stateValueText.setText("");
        		}
        	}
		});
                
        Button okButton = new Button(dialog, SWT.PUSH);
        okButton.setText("Ok");
        okButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		StateValue stateValue = StatemachineFactory.eINSTANCE.createStateValue();
        		stateValue.setValue(stateValueText.getText());
        		stateValue.setType(util.getStateValueTypeFromindex(stateValueTypeCombo.getSelectionIndex()));
        		stateChange.setStateValue(stateValue);
        		stateChange.getStateAttribute().addAll(selectedPath.getAllStateAttribute());
        		saveStateChange(stateChange);
        		dialog.close();
        	}
		});
        Button cancelButton = new Button(dialog, SWT.PUSH);
        cancelButton.setText("Cancel");
        cancelButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		dialog.close();
        	}
		});
        
        dialog.pack();
        dialog.open();
		while (!dialog.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
	
	private void saveStateChange(final StateChange stateChange) {
		TableItem stateChangeItem = new TableItem(stateChangeTable, 0);
		stateChangeItem.setText(stateChange.toString());
		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {

			@Override
			public boolean canExecute(IContext arg0) {
				return true;
			}

			@Override
			public void execute(IContext arg0) {
				if(getTransition() != null) {
					getTransition().getStateChange().add(stateChange);
				}
			}
		};
		CustomContext context = new CustomContext();
		execute(feature, context);
	}
	
	private void removeStateChange(final int stateChangeTableIndex) {
		stateChangeTable.remove(stateChangeTableIndex);
		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {

			@Override
			public boolean canExecute(IContext arg0) {
				return true;
			}

			@Override
			public void execute(IContext arg0) {
				if(getTransition() != null) {
					getTransition().getStateChange().remove(stateChangeTableIndex);
				}
			}
		};
		CustomContext context = new CustomContext();
		execute(feature, context);
	}
	
	private AbstractTransition getTransition() {
		PictogramElement pe = getSelectedPictogramElement();
		if (pe != null) {
			Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
			if(bo instanceof AbstractTransition) {
				return (AbstractTransition) bo;
			}        					
		}
		return null;
	}
}
