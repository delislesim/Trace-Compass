package org.eclipse.tracecompass.internal.tmf.analysis.xml.ui.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class ManageXmlAnalysisDialog extends Dialog {

    List xmlAnalysisList;
    Button newButton;
    Button editButton;
    Button deleteButton;

    /**
     * Constructor
     *
     * @param parent
     *            Parent shell of this dialog
     */
    public ManageXmlAnalysisDialog(Shell parent) {
        super(parent);
        setShellStyle(SWT.RESIZE | SWT.MAX | getShellStyle());
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        getShell().setText("Manage XML analysis");
        Composite composite = (Composite) super.createDialogArea(parent);
        composite.setLayout(new GridLayout(2, false));

        xmlAnalysisList = new List(composite, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        xmlAnalysisList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        xmlAnalysisList.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {}

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (xmlAnalysisList.getSelectionCount() == 0) {
                    editButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                } else {
                    editButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                }
            }
        });

        Composite buttonGroup = new Composite(composite, SWT.NONE);
        buttonGroup.setLayout(new GridLayout());
        buttonGroup.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));

        newButton = new Button(buttonGroup, SWT.PUSH);
        newButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        newButton.setText("New");
        newButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {}

            @Override
            public void widgetSelected(SelectionEvent e) {
//                WizardDialog dialog = null;
//                dialog = new WizardDialog(getShell(), new StatemachineDiagramWizard());
//                if (dialog != null) {
//                    dialog.open();
//                    if (dialog.getReturnCode() == Window.OK) {
//                        fillAnalysisList();
//                    }
//                }
            }
        });

        editButton = new Button(buttonGroup, SWT.PUSH);
        editButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        editButton.setText("Edit");
        editButton.setEnabled(false);

        deleteButton = new Button(buttonGroup, SWT.PUSH);
        deleteButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        deleteButton.setText("Delete");
        deleteButton.setEnabled(false);

        fillAnalysisList();

        getShell().setMinimumSize(300, 250);
        return composite;
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.CLOSE_LABEL, false);
    }

    private void fillAnalysisList() {

    }
}
