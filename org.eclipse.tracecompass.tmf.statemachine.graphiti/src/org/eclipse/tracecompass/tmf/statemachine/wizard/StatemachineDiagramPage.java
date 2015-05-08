package org.eclipse.tracecompass.tmf.statemachine.wizard;

import java.io.File;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.tracecompass.tmf.attributetree.core.utils.AttributeTreeUtils;

public class StatemachineDiagramPage extends WizardPage {
	
	private String diagramName;
	private String treeName;
	private String treePath;
	
	private Text diagramNameText;
	private Text treeNameText;
	private Text treePathText;
	
	private Button existingFileButton;
	
	private IStructuredSelection selectedElement;

	protected StatemachineDiagramPage(String pageName, IStructuredSelection selection) {
		super(pageName);
		setTitle("Diagram");
		setDescription("Enter diagram name");
		selectedElement = selection;
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		
		Label diagramNameLabel = new Label(composite, SWT.NONE);
		diagramNameLabel.setText("Diagram name");
		
		diagramNameText = new Text(composite, SWT.SINGLE);
		diagramNameText.setText("");
		diagramNameText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		diagramNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}			
		});
		
		// Attribute tree creation
		Group attributeTreeGroup = new Group(composite, SWT.NONE);
		attributeTreeGroup.setLayout(new GridLayout(2, false));
		attributeTreeGroup.setText("Attribute tree");
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.horizontalSpan = layout.numColumns;
		attributeTreeGroup.setLayoutData(gridData);
		
		Label treeNameLabel = new Label(attributeTreeGroup, SWT.NONE);
		treeNameLabel.setText("Name");
		
		treeNameText = new Text(attributeTreeGroup, SWT.SINGLE);
		treeNameText.setText("");
		treeNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		existingFileButton = new Button(attributeTreeGroup, SWT.CHECK);
		existingFileButton.setText("Use an existing file");
		gridData = new GridData(SWT.BEGINNING, SWT.FILL, true, false);
		gridData.horizontalSpan = layout.numColumns;
		existingFileButton.setLayoutData(gridData);
		
		treePathText = new Text(attributeTreeGroup, SWT.SINGLE | SWT.READ_ONLY);
		treePathText.setText("");
		treePathText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		treePathText.setEnabled(false);
		
		final Button browseButton = new Button(attributeTreeGroup, SWT.PUSH);
		browseButton.setText("Browse");
		browseButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, false));
		browseButton.setEnabled(false);
		
		// Listeners
		existingFileButton.addSelectionListener(new SelectionListener() {			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(existingFileButton.getSelection()) {
					treePathText.setEnabled(true);
					browseButton.setEnabled(true);
				} else {
					treePathText.setEnabled(false);
					browseButton.setEnabled(false);
				}
			}			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		browseButton.addSelectionListener(new SelectionListener() {			
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog openDialog = new FileDialog(new Shell(), SWT.OPEN);
				openDialog.setFilterNames(new String[] { "Attribute Tree" + " (*.attributetree)"}); //$NON-NLS-1$
				openDialog.setFilterExtensions(new String[] { "*.attributetree"}); //$NON-NLS-1$

		        treePathText.setText(openDialog.open());
		        setPageComplete(validatePage());
			}			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		treeNameText.addModifyListener(new ModifyListener() {			
			@Override
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}
		});
		
		setPageComplete(validatePage());

		setErrorMessage(null);
		setMessage(null);
		setControl(composite);
	}
	
	private boolean validatePage() {
		setErrorMessage(null);
		setMessage(null);
		diagramName = diagramNameText.getText();
		if(diagramName.equals("")) {
			setMessage("Please enter diagram name");
			return false;
		}
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IStatus diagramNameStatus = workspace.validateName(diagramName, IResource.FILE);
		if(!diagramNameStatus.isOK()) {
			setErrorMessage(diagramNameStatus.getMessage());
			return false;
		}
		if(!workspaceNameValidation(diagramName + ".diagram")) {
			setErrorMessage("Diagram name already exist");
			return false;
		}	
		
		if(!existingFileButton.getSelection()) {
			treeName = treeNameText.getText();
			if(treeName.equals("")) {
				setMessage("Please enter tree name");
				return false;
			}
			
			IStatus treeNameStatus = workspace.validateName(treeName, IResource.FILE);	
			if(!treeNameStatus.isOK()) {
				setErrorMessage(treeNameStatus.getMessage());
				return false;
			}			
			if(!workspaceNameValidation(treeNameText + ".attributetree")) {
				setErrorMessage("Tree name already exist");
				return false;
			}
		} else {
			treePath = treePathText.getText();
			if(treePath.equals("")) {
				return false;
			}
			// TODO activate the file validation
//			if(!AttributeTreeUtils.attributeTreeXmlValidate(new File(treePath))) {
//				return false;
//			}
		}

		return true;
	}
	
	private boolean workspaceNameValidation(String fileName) {
		Object element = selectedElement.getFirstElement();
		IResource resource = null;
		if (element instanceof IProject) {
			IFolder diagramsFolder = ((IProject)element).getFolder("Statemachine/Diagrams");
			resource = diagramsFolder.findMember(fileName);
		} else if (element instanceof IFolder) {
			resource = ((IFolder)element).findMember(fileName);
		}
		
		if(resource != null) {
			return false;
		}
		
		return true;
	}
	
	public String getDiagramName() {
		return diagramName;
	}
	
	public String getNewTreeName() {
		return treeName;
	}
	
	public String getExistingTreePath() {
		return treePath;
	}
	
	public boolean getUseExistingFile() {
		return existingFileButton.getSelection();
	}
}