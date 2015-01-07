package org.eclipse.tracecompass.tmf.statemachine.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class StatemachineDiagramPage extends WizardPage {
	
	private String diagramName;
	
	private Text diagramNameText;

	protected StatemachineDiagramPage(String pageName) {
		super(pageName);
		setTitle("Diagram");
		setDescription("Enter diagram name");
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
		diagramNameText.addModifyListener(new ModifyListener() {
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
		diagramName = diagramNameText.getText();
		if(diagramName.equals("")) {
			setMessage("Please enter diagram name");
			return false;
		}
		return true;
	}
	
	public String getDiagramName() {
		return diagramName;
	}

}