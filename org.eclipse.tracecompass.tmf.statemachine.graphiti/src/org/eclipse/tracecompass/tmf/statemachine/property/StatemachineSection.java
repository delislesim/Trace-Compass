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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AbstractAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeTreePath;
import org.eclipse.tracecompass.tmf.attributetree.core.utils.AttributeTreeUtils;
import org.eclipse.tracecompass.tmf.attributetree.ui.widgets.AttributeTreeComposite;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import statemachine.Statemachine;

public class StatemachineSection extends GFPropertySection implements ITabbedPropertyConstants {
	
	private Text statemachineNameText;
	private Text associatedTreeText;
	private Text attributePathText;
	
	private AttributeTreeComposite attributeTree;
	
	private AttributeTreePath selectedPath;

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
		
		Label associatedTree = factory.createLabel(composite, "Associated tree");
		
		associatedTreeText = factory.createText(composite, "");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		associatedTreeText.setLayoutData(gridData);
		associatedTreeText.setEnabled(false);
		
		// TODO Temporaire
		Label attributeTreePath = factory.createLabel(composite, "Attribute path");
		
		attributePathText = factory.createText(composite, "");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		attributePathText.setLayoutData(gridData);
		attributePathText.setEnabled(false);
		
        attributeTree = new AttributeTreeComposite(composite, SWT.NONE);
        attributeTree.getTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = attributeTree.getSelection();
				AbstractAttributeNode selectedNode = (AbstractAttributeNode)selection.getFirstElement();
				selectedPath = new AttributeTreePath(selectedNode);
				saveAssociatedAttribute();
			}
        	
        });
        
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
        		saveStatemachineName(statemachineName);
        	}
		});
		
//		associatedTreeText.addModifyListener(new ModifyListener() {
//
//        	@Override
//        	public void modifyText(ModifyEvent e) {
//        		String newTreeName = associatedTreeText.getText();
//        		PictogramElement pe = getSelectedPictogramElement();
//        		if (pe != null) {
//        			Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
//        			if (bo == null)
//        				return;
//        			String actualTreeName = null;
//        			if(bo instanceof Statemachine) {
//        				actualTreeName = ((Statemachine) bo).getAssociatedTree();
//        			}
//        			if (newTreeName.equals(actualTreeName)) {
//        				return;
//        			}
//        		}
//        		saveAssociatedTree(newTreeName);
//        	}
//		});
	}
	
	private void saveStatemachineName(final String name) {
		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
			
			@Override
			public void execute(IContext context) {
				PictogramElement pe = getSelectedPictogramElement();
				if (pe != null) {
					Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
					if (bo == null)
						return;
        			if(bo instanceof Statemachine) {
        				((Statemachine) bo).setName(name);
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
	
//	private void saveAssociatedTree(final String treeName) {
//		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
//			
//			@Override
//			public void execute(IContext context) {
//				PictogramElement pe = getSelectedPictogramElement();
//				if (pe != null) {
//					Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
//					if (bo == null)
//						return;
//        			if(bo instanceof Statemachine) {
//        				((Statemachine) bo).setAssociatedTree(treeName);
//        			}        					
//				}
//			}
//			
//			@Override
//			public boolean canExecute(IContext context) {
//				return true;
//			}
//		};
//		CustomContext context = new CustomContext();
//		execute(feature, context);
//	}
	
	private void saveAssociatedAttribute() {
		if(selectedPath != null) {
			IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
				
				@Override
				public void execute(IContext context) {
					PictogramElement pe = getSelectedPictogramElement();
					if (pe != null) {
						Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
						if (bo == null)
							return;
	        			if(bo instanceof Statemachine) {
	        				((Statemachine) bo).setAssociatedAttribute(selectedPath.getPathFromAttributeTreePath());
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
        		
        		String associatedTree = ((Statemachine)bo).getAssociatedTree();
        		associatedTreeText.setText((associatedTree != null) ? associatedTree : "");
        		
        		String attributePath = ((Statemachine)bo).getAssociatedAttribute();
        		attributePathText.setText((attributePath != null) ? attributePath : "");
        		
        		attributeTree.setTreeViewerInput(AttributeTreeUtils.getAttributeTreeFile(getDiagram().getName()));
        	}
        }
	}
}
