package org.eclipse.tracecompass.tmf.attributetree.ui.views;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.tracecompass.tmf.ui.views.TmfView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AbstractAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeValueNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.ConstantAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.VariableAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.ui.Activator;
import org.eclipse.tracecompass.tmf.attributetree.ui.widgets.AttributeTreeComposite;
import org.eclipse.ui.IActionBars;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AttributeTreeView extends TmfView {
	
	private Composite composite;
	private AttributeTreeComposite attributeTree;
	
	private static final String ATTRIBUTETREE_XML_DIRECTORY = "attributetree_xml_files";
	
	private enum NodeType {
		CONSTANT, VARIABLE, VALUE
	}

	public AttributeTreeView() {
		super("org.eclipse.tracecompass.tmf.statemachine.ui.attributeTreeView");
	}

	@Override
	public void createPartControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(5, false));
		
		GridData gridData;
		
		Button addConstantAttributeButton = new Button(composite, SWT.PUSH);
		addConstantAttributeButton.setText("Constant");
		gridData = new GridData();
		addConstantAttributeButton.setLayoutData(gridData);
		
		Button addVariableAttributeButton = new Button(composite, SWT.PUSH);
		addVariableAttributeButton.setText("Variable");
		gridData = new GridData();
		addConstantAttributeButton.setLayoutData(gridData);
		
		Button addAttributeValueButton = new Button(composite, SWT.PUSH);
		addAttributeValueButton.setText("Value");
		gridData = new GridData();
		addConstantAttributeButton.setLayoutData(gridData);
		
		Button removeAttributeButton = new Button(composite, SWT.PUSH);
		removeAttributeButton.setText("Remove");
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
		editAttributeButton.setText("Edit");
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
		IPath xmlPath = Activator.getDefault().getStateLocation();
		xmlPath = xmlPath.addTrailingSeparator().append(ATTRIBUTETREE_XML_DIRECTORY);
		xmlPath = xmlPath.addTrailingSeparator().append("attributetree.xml");
		if(xmlPath.toFile().exists()) {
			attributeTree.setTreeViewerInput(xmlPath.toFile());
		} else {
			attributeTree.setTreeViewerInput(null);
		}
        
        IActionBars bars = getViewSite().getActionBars();
        bars.getToolBarManager().add(getSaveAction());
	}
	
	private Action getSaveAction() {
		Action saveAction = new Action("Save", IAction.AS_PUSH_BUTTON) {
			@Override
            public void run() {
				Document xmlFile = null;
    			try {
    				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
    						.newInstance();
    				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    				xmlFile = dBuilder.newDocument();
    			} catch (ParserConfigurationException exception) {
    			}
    			
    			Element rootElement = attributeTree.getRoot().createElement(attributeTree.getRoot(), xmlFile);
    			xmlFile.appendChild(rootElement);
    			try {
    				TransformerFactory transformerFactory = TransformerFactory
    						.newInstance();
    				Transformer transformer = transformerFactory.newTransformer();
    				DOMSource source = new DOMSource(xmlFile);
    				
    				IPath xmlPath = Activator.getDefault().getStateLocation();
    				xmlPath = xmlPath.addTrailingSeparator().append(ATTRIBUTETREE_XML_DIRECTORY);
    		        /* Check if directory exists, otherwise create it */
    		        File dir = xmlPath.toFile();
    		        if (!dir.exists() || !dir.isDirectory()) {
    		            dir.mkdirs();
    		        }
    		        xmlPath = xmlPath.addTrailingSeparator().append("attributetree.xml");
    				StreamResult result = new StreamResult(xmlPath.toFile());
    				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    				transformer.transform(source, result);
    			} catch (TransformerException exception) {
    			}
			}
		};
		saveAction.setText("Save");
		return saveAction;
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
}
