package org.eclipse.tracecompass.tmf.statemachine.property;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
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
import org.eclipse.swt.widgets.TableColumn;
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

import statemachine.AbstractCondition;
import statemachine.AttributeCondition;
import statemachine.ConditionalState;
import statemachine.FieldCondition;
import statemachine.StateValue;
import statemachine.StatemachineFactory;

public class ConditionalStateSection extends GFPropertySection implements ITabbedPropertyConstants {
	private Text conditionNameText;
	//private Button isNotConditionButton;
	private Table conditionTable;
	
//	private Button ANDButton;
//	private Button ORButton;
	
	private AttributeTreePath selectedPath;
	
	private ConvertStatemachineType statemachineUtil = new ConvertStatemachineType();
	
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        
        TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
        final Composite composite = factory.createFlatFormComposite(parent);
        composite.setLayout(new GridLayout(2, false));
        GridData gridData;
        
		Label label = factory.createLabel(composite, "Name");
        
        conditionNameText = factory.createText(composite, "");
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        conditionNameText.setLayoutData(gridData);
        
        conditionTable = factory.createTable(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.FILL;
        gridData.verticalSpan = 4;
        conditionTable.setLayoutData(gridData);
        
        Button fieldConditionButton = factory.createButton(composite, "Add field condition", SWT.PUSH);
        
        Button attributeConditionButton = factory.createButton(composite, "Add condition with attribute", SWT.PUSH);
        
        Button removeConditionButton = factory.createButton(composite, "Remove condition", SWT.PUSH);
        
        Button organizeConditionsButton = factory.createButton(composite, "Organize conditions", SWT.PUSH);
        
        /*Group expressionGroup = new Group(composite, SWT.NONE);
        expressionGroup.setText("Expression type");
        expressionGroup.setLayout(new GridLayout(2, false));
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        expressionGroup.setLayoutData(gridData);
        
        ANDButton = factory.createButton(expressionGroup, "AND", SWT.RADIO);
        ANDButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveExpressionType(true);
			}
		});
        
        ORButton = factory.createButton(expressionGroup, "OR", SWT.RADIO);
        ORButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveExpressionType(false);
			}
		});*/
        
		fieldConditionButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addFieldConditionDialog(composite.getDisplay());
			}
		});
        
		attributeConditionButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addAttributeConditionDialog(composite.getDisplay());
			}
		});
		
		removeConditionButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int removeConditionIndex = conditionTable.getSelectionIndex();
				if (removeConditionIndex >= 0) {
					boolean confirm = MessageDialog.openConfirm(composite.getShell(), "Remove condition", "Are you sure you want to delete this condition? It will destroy your conditions organization");
					if(confirm) {
						conditionTable.remove(removeConditionIndex);
						removeCondition(removeConditionIndex);
					}
				}
			}
		});
		
		organizeConditionsButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(conditionTable.getItemCount() > 1) {
					organizeConditionDialog(composite.getDisplay());
				}
			}
		});
        
        conditionNameText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				String newConditionName = conditionNameText.getText();
				if(newConditionName == null) {
					newConditionName = "";
				}
				PictogramElement pe = getSelectedPictogramElement();
        		if (pe != null) {
        			Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
        			if (bo == null)
        				return;
        			String actualConditionName = null;
        			if(bo instanceof ConditionalState) {
        				actualConditionName = ((ConditionalState)bo).getName();
        			}
        			if(newConditionName.equals(actualConditionName))
        				return;
        		}
        		final String conditionName = newConditionName;
        		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
					
					@Override
					public void execute(IContext context) {
						PictogramElement pe = getSelectedPictogramElement();
        				if (pe != null) {
        					Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
        					if (bo == null)
        						return;
                			if(bo instanceof ConditionalState) {
                				((ConditionalState) bo).setName(conditionName);
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
        	
        	if(bo instanceof ConditionalState) {
        		boolean hasCondtion = !((ConditionalState)bo).getCondition().isEmpty();
        		// Refresh condition name
        		String conditionName = ((ConditionalState)bo).getName();
        		conditionNameText.setText((conditionName != null) ? conditionName : "");

        		// Refresh condition table
        		conditionTable.removeAll();
        		if(hasCondtion) {
        			for(int i = 0; i < ((ConditionalState)bo).getCondition().size(); i++) {
    	        		TableItem conditionItem = new TableItem(conditionTable, SWT.NONE);
    	        		String conditionType = ((ConditionalState)bo).getCondition().get(i).toString(); //(((ConditionalState)bo).getCondition().get(i) instanceof FieldCondition ? "Field condition" : "Attribute condition");
    	        		String notCondition = ((ConditionalState)bo).getCondition().get(i).isIsNotCondition() ? "NOT " : "";
    	        		conditionItem.setText(notCondition + conditionType);
        			}
        		}
        		
        		// Refresh expression type
//        		ANDButton.setSelection(((ConditionalState)bo).isAndExpression());
//        		ORButton.setSelection(!((ConditionalState)bo).isAndExpression());
        	}
        }
    }
	
	private void addFieldConditionDialog(Display display) {
		final Shell dialog = new Shell(display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        GridLayout dialogLayout = new GridLayout(2, false);
        dialog.setLayout (dialogLayout);
        dialog.setText("Field condition");
        
        GridData gridData;
        
        Label fieldNameLabel = new Label(dialog, SWT.NONE);
        fieldNameLabel.setText("Field name");
        
        final Text fieldNameText = new Text(dialog, SWT.SINGLE);
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        fieldNameText.setLayoutData(gridData);
        
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
        stateValueTypeCombo.setItems(statemachineUtil.getStateValueTypeString());
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
        
		final Button isNotConditionButton = new Button(dialog, SWT.CHECK);
		isNotConditionButton.setText("Not condition");
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        isNotConditionButton.setLayoutData(gridData);
        
        Button cancelButton = new Button(dialog, SWT.PUSH);
        cancelButton.setText("Cancel");
        
        cancelButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		dialog.close();
        	}
        });
        
        Button okButton = new Button(dialog, SWT.PUSH);
        okButton.setText("Add");
        
        okButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		final FieldCondition condition = StatemachineFactory.eINSTANCE.createFieldCondition();
        		condition.setFieldName(fieldNameText.getText());
        		StateValue stateValue = StatemachineFactory.eINSTANCE.createStateValue();
        		stateValue.setType(statemachineUtil.getStateValueTypeFromindex(stateValueTypeCombo.getSelectionIndex()));
        		stateValue.setValue(stateValueText.getText());
        		condition.setStateValue(stateValue);
        		
        		condition.setIsNotCondition(isNotConditionButton.getSelection());
        		
        		saveCondition(condition);
        		
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
	
	private void addAttributeConditionDialog(Display display) {
		final Shell dialog = new Shell(display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        GridLayout dialogLayout = new GridLayout(2, false);
        dialog.setLayout (dialogLayout);
        dialog.setText("Attribute condition");
        
        GridData gridData;
        
        final AttributeCondition condition = StatemachineFactory.eINSTANCE.createAttributeCondition();
        
        Group stateAttributeGroup = new Group(dialog, SWT.NONE);
        stateAttributeGroup.setText("State attribute");
        stateAttributeGroup.setLayout(new GridLayout(2, false));
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        stateAttributeGroup.setLayoutData(gridData);
        
        Group stateValueGroup = new Group(dialog, SWT.NONE);
        stateValueGroup.setText("State value");
        stateValueGroup.setLayout(new GridLayout(2, false));
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        stateValueGroup.setLayoutData(gridData);
        
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
        
        Label stateValueTypeLabel = new Label(stateValueGroup, SWT.NONE);
        stateValueTypeLabel.setText("Type");
        
        final Combo stateValueTypeCombo = new Combo(stateValueGroup, SWT.READ_ONLY);
        stateValueTypeCombo.setItems(statemachineUtil.getStateValueTypeString());
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
        
		final Button isNotConditionButton = new Button(dialog, SWT.CHECK);
		isNotConditionButton.setText("Not condition");
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        isNotConditionButton.setLayoutData(gridData);
        
        Button cancelButton = new Button(dialog, SWT.PUSH);
        cancelButton.setText("Cancel");
        
        cancelButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		dialog.close();
        	}
        });
        
        Button okButton = new Button(dialog, SWT.PUSH);
        okButton.setText("Ok");
        okButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		condition.getStateAttribute().addAll(selectedPath.getAllStateAttribute());
        		
        		StateValue stateValue = StatemachineFactory.eINSTANCE.createStateValue();
        		stateValue.setType(statemachineUtil.getStateValueTypeFromindex(stateValueTypeCombo.getSelectionIndex()));
        		stateValue.setValue(stateValueText.getText());
        		condition.setStateValue(stateValue);
        		condition.setIsNotCondition(isNotConditionButton.getSelection());
        		
        		saveCondition(condition);
        		
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
	
	private void organizeConditionDialog(Display display) {
		final Shell dialog = new Shell(display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        GridLayout dialogLayout = new GridLayout(2, false);
        dialog.setLayout (dialogLayout);
        dialog.setText("Organize conditions");
        
        GridData gridData;
        Table possibleCondition = new Table(dialog, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.HIDE_SELECTION);
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        possibleCondition.setLayoutData(gridData);
        
        TableColumn indexColumn = new TableColumn(possibleCondition, SWT.NONE);
        indexColumn.setText("Index");
        TableColumn conditionColumn = new TableColumn(possibleCondition, SWT.NONE);
        conditionColumn.setText("Condition");
        
        for(int i = 0; i < conditionTable.getItemCount(); i++) {
        	TableItem item = new TableItem(possibleCondition, SWT.NONE);
        	Integer index = i;
        	item.setText(0, index.toString());
        	item.setText(1, conditionTable.getItem(i).getText());
        }
        
        possibleCondition.getColumn(0).pack();
        possibleCondition.getColumn(1).pack();
        
        Group expressionGroup = new Group(dialog, SWT.NONE);
        expressionGroup.setText("Expression type");
        expressionGroup.setLayout(new GridLayout(2, false));
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        expressionGroup.setLayoutData(gridData);
        
        Button ANDButton = new Button(expressionGroup, SWT.RADIO);
        ANDButton.setText("AND");
        
        Button ORButton = new Button(expressionGroup, SWT.RADIO);
        ORButton.setText("OR");
        
        final Text conditionOrganizationText = new Text(dialog, SWT.SINGLE);
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        conditionOrganizationText.setLayoutData(gridData);
        conditionOrganizationText.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(conditionOrganizationText.getText().isEmpty()) {
					conditionOrganizationText.setBackground(new Color(null, new RGB(255, 0, 0)));
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				conditionOrganizationText.setBackground(null);
			}
		});
        
        PictogramElement pe = getSelectedPictogramElement();
        if (pe != null) {
        	Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
        	if (bo == null)
                return;
        	
        	if(bo instanceof ConditionalState) {
        		String organization = ((ConditionalState)bo).getConditionsOrganization() != null ? ((ConditionalState)bo).getConditionsOrganization() : "";
        		conditionOrganizationText.setText(organization);
        	}
        }
        
        ANDButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditionOrganizationText.setBackground(null);
				conditionOrganizationText.setText(conditionBuilder("AND"));
			}
		});
        
        ORButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditionOrganizationText.setBackground(null);
				conditionOrganizationText.setText(conditionBuilder("OR"));
			}
		});
        
        Label tooltipLabel = new Label(dialog, SWT.NONE);
        tooltipLabel.setText("ex: (0 AND 1) OR (2 AND 3)");
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        tooltipLabel.setLayoutData(gridData);
        
        Button cancelButton = new Button(dialog, SWT.PUSH);
        cancelButton.setText("Cancel");
        
        cancelButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		dialog.close();
        	}
        });
        
        Button okButton = new Button(dialog, SWT.PUSH);
        okButton.setText("Ok");
        okButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		if(validateOrganization(conditionOrganizationText.getText())) {
	        		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
	
	        			@Override
	        			public boolean canExecute(IContext arg0) {
	        				return true;
	        			}
	
	        			@Override
	        			public void execute(IContext arg0) {
	        				PictogramElement pe = getSelectedPictogramElement();
	        				if (pe != null) {
	        					Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
	        					if (bo == null)
	        						return;
	                			if(bo instanceof ConditionalState) {
	                				((ConditionalState) bo).setConditionsOrganization(conditionOrganizationText.getText());;
	                			}        					
	        				}
	        			}
	        		};
	        		CustomContext context = new CustomContext();
	        		execute(feature, context);
	        		
	        		dialog.close();
        		} else {
        			conditionOrganizationText.setBackground(new Color(null, new RGB(255, 0, 0)));
        		}
        	}
        });
        
        dialog.pack();
        dialog.open();
		while (!dialog.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
	
	private boolean validateOrganization(String organization) {
		if(organization.isEmpty()) {
			return false;
		}
		//TODO Validate if all condition are used and only AND or OR is used
		return true;
	}
	
	private String conditionBuilder(String operator) { //AND or OR
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < conditionTable.getItemCount(); i++) {
			builder.append(i);
			if(i != conditionTable.getItemCount() - 1) {
				builder.append(" " + operator + " ");
			}
		}
		return builder.toString();
	}
	
	private void removeCondition(final int index) {
		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {

			@Override
			public boolean canExecute(IContext arg0) {
				return true;
			}

			@Override
			public void execute(IContext arg0) {
				PictogramElement pe = getSelectedPictogramElement();
				if (pe != null) {
					Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
					if (bo == null)
						return;
        			if(bo instanceof ConditionalState) {
        				((ConditionalState) bo).getCondition().remove(index);
        				((ConditionalState) bo).setConditionsOrganization(conditionBuilder("AND"));
        			}        					
				}
			}
		};
		CustomContext context = new CustomContext();
		execute(feature, context);
	}
	
//	private void saveExpressionType(final boolean isAndExpression) {
//		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
//
//			@Override
//			public boolean canExecute(IContext arg0) {
//				return true;
//			}
//
//			@Override
//			public void execute(IContext arg0) {
//				PictogramElement pe = getSelectedPictogramElement();
//				if (pe != null) {
//					Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
//					if (bo == null)
//						return;
//        			if(bo instanceof ConditionalState) {
//        				((ConditionalState) bo).setAndExpression(isAndExpression);
//        			}        					
//				}
//			}
//		};
//		CustomContext context = new CustomContext();
//		execute(feature, context);	
//	}
	
	private void saveCondition(final AbstractCondition condition) {
		TableItem conditionItem = new TableItem(conditionTable, SWT.NONE);
		String conditionType = condition.toString(); //(condition instanceof FieldCondition ? "Field condition" : "Attribute condition");
		String notCondition = condition.isIsNotCondition() ? "NOT " : "";
		conditionItem.setText(notCondition + conditionType);
		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {

			@Override
			public boolean canExecute(IContext arg0) {
				return true;
			}

			@Override
			public void execute(IContext arg0) {
				PictogramElement pe = getSelectedPictogramElement();
				if (pe != null) {
					Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
					if (bo == null)
						return;
        			if(bo instanceof ConditionalState) {
        				((ConditionalState) bo).getCondition().add(condition);
        				((ConditionalState) bo).setConditionsOrganization(((ConditionalState) bo).getConditionsOrganization() + " AND " + (conditionTable.getItemCount() - 1));
        			}        					
				}
			}
		};
		CustomContext context = new CustomContext();
		execute(feature, context);		
	}
}
