package org.eclipse.tracecompass.tmf.attributetree.ui.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AttributeTreeWizardPage extends WizardPage {
	
	private String attributeTreeName;
	private Text nameText;
	
	protected AttributeTreeWizardPage(String pageName) {
		super(pageName);
		setTitle("Attribute tree");
		setDescription("Enter an attribute tree name");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		
		Label nameLabel = new Label(composite, SWT.NONE);
		nameLabel.setText("Attribute tree name");
		
		nameText = new Text(composite, SWT.SINGLE);
		nameText.setText("");
		nameText.addModifyListener(new ModifyListener() {			
			@Override
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}
		});
		
		setControl(composite);
		setPageComplete(false);
	}
	
	private boolean validatePage() {
		// TODO : Workspace validation (duplicate name)
		attributeTreeName = nameText.getText();
		if(attributeTreeName.equals("")) {
			setMessage("Please enter attribute tree name");
			return false;
		}
		return true;
	}
	
	public String getAttributeTreeName() {
		return attributeTreeName;
	}
}
