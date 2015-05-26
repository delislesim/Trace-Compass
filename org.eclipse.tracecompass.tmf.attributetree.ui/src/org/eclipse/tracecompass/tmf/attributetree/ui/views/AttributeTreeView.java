package org.eclipse.tracecompass.tmf.attributetree.ui.views;

import java.io.File;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.tracecompass.tmf.ui.views.TmfView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AbstractAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeTree;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeTreePath;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeValueNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.ConstantAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.VariableAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.ui.Activator;
import org.eclipse.tracecompass.tmf.attributetree.ui.widgets.AttributeTreeComposite;
import org.eclipse.ui.IActionBars;

public class AttributeTreeView extends TmfView {
	
	private Composite composite;
	private AttributeTreeComposite attributeTree;
	
	private File openedFile;
	private String LAST_OPENED_KEY = "lastOpenedFile";
	
	private int GRID_NUM_COLUMNS = 3;
	
	private enum NodeType {
		CONSTANT, VARIABLE, VALUE
	}

	public AttributeTreeView() {
		super("org.eclipse.tracecompass.tmf.statemachine.ui.attributeTreeView");
	}

	@Override
	public void createPartControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(GRID_NUM_COLUMNS, false));
		
		GridData gridData;
		
		// TODO : replace this images loading system ?
		Image addConstantImage = Activator.getDefault().getImageFromPath("/icons/addconstantAttribute.png");
		Image addVariableImage = Activator.getDefault().getImageFromPath("/icons/addvariableAttribute.png");
		Image addValueImage = Activator.getDefault().getImageFromPath("/icons/addvalue.png");
		Image removeImage = Activator.getDefault().getImageFromPath("/icons/removeAttribute.png");
		Image editAttributeImage = Activator.getDefault().getImageFromPath("/icons/rename.gif");;
		
		Button addConstantAttributeButton = new Button(composite, SWT.PUSH);
		//addConstantAttributeButton.setText("Constant");
		addConstantAttributeButton.setImage(addConstantImage);
		gridData = new GridData();
		addConstantAttributeButton.setLayoutData(gridData);
		
		Button addVariableAttributeButton = new Button(composite, SWT.PUSH);
		//addVariableAttributeButton.setText("Variable");
		addVariableAttributeButton.setImage(addVariableImage);
		gridData = new GridData();
		addConstantAttributeButton.setLayoutData(gridData);
		
		Button addAttributeValueButton = new Button(composite, SWT.PUSH);
		//addAttributeValueButton.setText("Value");
		addAttributeValueButton.setImage(addValueImage);
		gridData = new GridData();
		addConstantAttributeButton.setLayoutData(gridData);
		
		Button removeAttributeButton = new Button(composite, SWT.PUSH);
		//removeAttributeButton.setText("Remove");
		removeAttributeButton.setImage(removeImage);
		gridData = new GridData();
		removeAttributeButton.setLayoutData(gridData);
		
		removeAttributeButton.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			IStructuredSelection selection = (IStructuredSelection) attributeTree.getSelection();
    			if(!selection.isEmpty()) {
    				if(selection.getFirstElement() instanceof AbstractAttributeNode) {
    					removeAttribute((AbstractAttributeNode)selection.getFirstElement());
    				}
    			}
    		}
		});
		
		Button editAttributeButton = new Button(composite, SWT.PUSH);
		//editAttributeButton.setText("Edit");
		editAttributeButton.setImage(editAttributeImage);
		gridData = new GridData();
		editAttributeButton.setLayoutData(gridData);
		
		editAttributeButton.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			IStructuredSelection selection = (IStructuredSelection) attributeTree.getSelection();
    			if(!selection.isEmpty()) {
    				if(selection.getFirstElement() instanceof AbstractAttributeNode) {
    					editAttributeDialog(composite.getDisplay(), (AbstractAttributeNode)selection.getFirstElement());
    				}
    			}
    		}
		});
		
		// TODO : remove when right click will be implemented
		Button changeQueryVariableAttributeButton = new Button(composite, SWT.PUSH);
		changeQueryVariableAttributeButton.setText("Query");
		gridData = new GridData();
		changeQueryVariableAttributeButton.setLayoutData(gridData);
		
		changeQueryVariableAttributeButton.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			IStructuredSelection selection = (IStructuredSelection) attributeTree.getSelection();
    			if(!selection.isEmpty()) {
    				if(selection.getFirstElement() instanceof VariableAttributeNode) {
    					VariableAttributeNode queryNode = (VariableAttributeNode)selection.getFirstElement();
    					if(queryNode.getIsQuery()) {
    						queryNode.setIsQuery(false);
    						queryNode.setQueryPath(null);
    					} else {
	    					queryDialog(composite.getDisplay(), queryNode);
    					}
    					attributeTree.refresh();
    				}
    			}
    		}
		});
		
		addConstantAttributeButton.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			addAttribute(NodeType.CONSTANT);
    		}
		});
		
		addVariableAttributeButton.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			addAttribute(NodeType.VARIABLE);
    		}
		});
		
		addAttributeValueButton.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			addAttribute(NodeType.VALUE);
    		}
		});
		
		attributeTree = new AttributeTreeComposite(composite, SWT.NONE);
		String lastOpenedFilePath = Activator.getDefault().getDialogSettings().get(LAST_OPENED_KEY);
		if(lastOpenedFilePath != null) {
			openedFile = new File(lastOpenedFilePath);
			if(openedFile.exists()) {
				attributeTree.setTreeViewerInput(openedFile);
			}
		}
		
		setViewInformation(openedFile);
        
        IActionBars bars = getViewSite().getActionBars();
        //bars.getToolBarManager().add(getNewAction());
        bars.getToolBarManager().add(getOpenAction());
        bars.getToolBarManager().add(getSaveAction());
	}
	
	private Action getSaveAction() {
		Action saveAction = new Action("Save", IAction.AS_PUSH_BUTTON) {
			@Override
            public void run() {
				AttributeTree.getInstance().saveAttributeTree(openedFile);
//				// TODO If openedFile doesn't exist it crash
//				Document xmlFile = null;
//    			try {
//    				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
//    						.newInstance();
//    				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//    				xmlFile = dBuilder.newDocument();
//    			} catch (ParserConfigurationException exception) {
//    			}
//    			
//    			Element rootElement = attributeTree.getRoot().createElement(attributeTree.getRoot(), xmlFile);
//    			xmlFile.appendChild(rootElement);
//    			try {
//    				TransformerFactory transformerFactory = TransformerFactory
//    						.newInstance();
//    				Transformer transformer = transformerFactory.newTransformer();
//    				DOMSource source = new DOMSource(xmlFile);
//    				
//    				StreamResult savedFileResult = new StreamResult(openedFile);
//    				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//    				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//    				transformer.transform(source, savedFileResult);
//    			} catch (TransformerException exception) {
//    			}
			}
		};
		saveAction.setImageDescriptor(Activator.getDefault().getImageDescripterFromPath("/icons/save_button.gif"));
		//saveAction.setText("Save");
		return saveAction;
	}
	
	private Action getOpenAction() {
		Action openAction = new Action("Open", IAction.AS_PUSH_BUTTON) {
			@Override
            public void run() {
				FileDialog openDialog = new FileDialog(new Shell(), SWT.OPEN);
				openDialog.setFilterNames(new String[] { "Attribute Tree" + " (*.attributetree)"}); //$NON-NLS-1$
				openDialog.setFilterExtensions(new String[] { "*.attributetree"}); //$NON-NLS-1$
				
				// Default path for attribute tree selection (the workspace)
				String defaultAttributeTreePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
				openDialog.setFilterPath(defaultAttributeTreePath);
				
		        String filePath = openDialog.open();
				if (filePath != null) {
					openedFile = new File(filePath);
					attributeTree.setTreeViewerInput(openedFile);
					IDialogSettings settings = Activator.getDefault().getDialogSettings();
					settings.put(LAST_OPENED_KEY, filePath);
				}
				setViewInformation(openedFile);
			}
		};
		openAction.setImageDescriptor(Activator.getDefault().getImageDescripterFromPath("/icons/open.gif"));
		return openAction;
	}
	
	private Action getNewAction() {
		Action newAction = new Action("New tree", IAction.AS_PUSH_BUTTON) {
			@Override
            public void run() {
				FileDialog saveDialog = new FileDialog(new Shell(), SWT.SAVE);
				saveDialog.setFilterNames(new String[] { "Attribute Tree" + " (*.attributetree)"}); //$NON-NLS-1$
				saveDialog.setFilterExtensions(new String[] { "*.attributetree"}); //$NON-NLS-1$
				
		        String filePath = saveDialog.open();
		        File treeFile = new File(filePath);
		        attributeTree.setTreeViewerInput(treeFile);
			}
		};
		newAction.setImageDescriptor(Activator.getDefault().getImageDescripterFromPath("/icons/new.gif"));
		return newAction;
	}
	
	private void setViewInformation(File file) {
		if(file == null) {
			setPartName("Attribute Tree View");
		} else {
			String fileName = file.getName();
			String viewTitle = fileName.substring(0, fileName.indexOf(".")) + " (" + file.getAbsolutePath() + ")";
			setPartName(viewTitle);
		}
	}

	@Override
	public void setFocus() {
		composite.setFocus();
	}
	
	private void removeAttribute(AbstractAttributeNode node) {
		node.getParent().removeChild(node);
		attributeTree.refresh();
	}
	
	private void addAttribute(NodeType type) {
		IStructuredSelection selection = (IStructuredSelection) attributeTree.getSelection();
		AbstractAttributeNode parent;
		if(selection.isEmpty()) {
			parent = attributeTree.getRoot();
		} else {
			parent = (AbstractAttributeNode) selection.getFirstElement();
		}
		
		switch(type) {
		case CONSTANT:
			new ConstantAttributeNode(parent);
			break;
		case VARIABLE:
			new VariableAttributeNode(parent);
			break;
		case VALUE:
			new AttributeValueNode(parent);
			break;
		}
		attributeTree.refresh();
	}
	
	private void editAttributeDialog(Display display, final AbstractAttributeNode attributeNode) {
		final Shell dialog = new Shell(display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setLayout (new GridLayout(2, false));
        dialog.setText("Attribtue name");
        
        GridData gridData;
        Label nameLabel = new Label(dialog, SWT.NONE);
        nameLabel.setText("Name");
        
        final Text attributeNameText = new Text(dialog, SWT.SINGLE);
        attributeNameText.setText(attributeNode.getName());
        attributeNameText.selectAll();
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        attributeNameText.setLayoutData(gridData);
        
        Button ok = new Button(dialog, SWT.PUSH);
        ok.setText("Ok");
        ok.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		attributeNode.setName(attributeNameText.getText());
        		attributeTree.refresh();
        		dialog.close();
        	}
		});
        
        Button cancel = new Button(dialog, SWT.PUSH);
        cancel.setText("Cancel");
        cancel.addSelectionListener(new SelectionAdapter() {
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
	
	private void queryDialog(Display display, final VariableAttributeNode queryNode) {
		final Shell dialog = new Shell(display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setLayout (new GridLayout(1, false));
        dialog.setText("Query path");
        
        final AttributeTreeComposite queryAttributeTree = new AttributeTreeComposite(dialog, SWT.NONE);
        queryAttributeTree.setTreeViewerInput(openedFile);
        
        Button selectButton = new Button(dialog, SWT.PUSH);
        selectButton.setText("Select");
        selectButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
				IStructuredSelection selection = queryAttributeTree.getSelection();
				AbstractAttributeNode selectedNode = (AbstractAttributeNode)selection.getFirstElement();
				queryNode.setIsQuery(true);
				queryNode.setQueryPath(new AttributeTreePath(selectedNode));
				dialog.close();
        	}
        });
//        queryAttributeTree.getTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {
//
//			@Override
//			public void selectionChanged(SelectionChangedEvent event) {
//				IStructuredSelection selection = queryAttributeTree.getSelection();
//				AbstractAttributeNode selectedNode = (AbstractAttributeNode)selection.getFirstElement();
//				queryNode.setIsQuery(true);
//				queryNode.setQueryPath(new AttributeTreePath(selectedNode));
//				dialog.close();
//			}
//        	
//        });
        
        dialog.pack();
        dialog.open();
		while (!dialog.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
}
