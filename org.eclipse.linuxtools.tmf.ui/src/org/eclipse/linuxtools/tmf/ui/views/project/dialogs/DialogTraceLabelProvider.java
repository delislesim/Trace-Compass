/*******************************************************************************
 * Copyright (c) 2009 Ericsson
 * 
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Francois Chouinard - Initial API and implementation
 *******************************************************************************/

package org.eclipse.linuxtools.tmf.ui.views.project.dialogs;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.linuxtools.tmf.ui.views.project.model.TmfTraceNode;
import org.eclipse.swt.graphics.Image;

/**
 * <b><u>TmfTracesLabelProvider</u></b>
 * <p>
 * TODO: Implement me. Please.
 */
public class DialogTraceLabelProvider extends LabelProvider implements ITableLabelProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
	 */
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof TmfTraceNode) {
			TmfTraceNode entry = (TmfTraceNode) element;
			switch (columnIndex) {
				case 0:
					return entry.getName();
				default:
					return null;
			}
		}
		return null;
	}

}
