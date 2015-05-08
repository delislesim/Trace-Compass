package org.eclipse.tracecompass.internal.tmf.analysis.xml.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.tracecompass.internal.tmf.analysis.xml.ui.dialogs.ManageXmlAnalysisDialog;

public class ManageXmlAnalysisHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ManageXmlAnalysisDialog dialog = new ManageXmlAnalysisDialog(Display.getDefault().getActiveShell());
        dialog.open();
        return null;
    }

}
