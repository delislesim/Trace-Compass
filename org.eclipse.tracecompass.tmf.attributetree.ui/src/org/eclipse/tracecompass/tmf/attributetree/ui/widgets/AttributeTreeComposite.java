package org.eclipse.tracecompass.tmf.attributetree.ui.widgets;

import java.io.File;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AbstractAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeTree;
import org.eclipse.tracecompass.tmf.attributetree.ui.views.AttributeTreeContentProvider;
import org.eclipse.tracecompass.tmf.attributetree.ui.views.AttributeTreeLabelProvider;

public class AttributeTreeComposite extends Composite {
	
	private TreeViewer treeViewer;
	private File fileInput;

	public AttributeTreeComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		
		treeViewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
		
		treeViewer.getTree().setLayout(new GridLayout(1, false));
		GridData gridData = new GridData();
		gridData.horizontalSpan = 6;
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.verticalAlignment = SWT.FILL;
        gridData.grabExcessVerticalSpace = true;
        treeViewer.getTree().setLayoutData(gridData);
        
        treeViewer.setAutoExpandLevel(3);
		treeViewer.setContentProvider(new AttributeTreeContentProvider());
		treeViewer.setLabelProvider(new AttributeTreeLabelProvider());
		
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				if(!selection.isEmpty()) {
					treeViewer.setExpandedState(selection.getFirstElement(), !treeViewer.getExpandedState(selection.getFirstElement()));
				}
			}
		});
	}
	
	public AbstractAttributeNode getRoot() {
		if(fileInput != null) {
			return AttributeTree.getInstance().getRoot(fileInput);
		}
		
		return null;
	}
	
	public TreeViewer getTreeViewer() {
		return treeViewer;
	}
	
	public void setTreeViewerInput(File xmlTree) {
		fileInput = xmlTree;
		AbstractAttributeNode treeInput = getRoot();
		treeViewer.setInput(treeInput);
	}
	
	public IStructuredSelection getSelection() {
		return (IStructuredSelection) treeViewer.getSelection();
	}
	
	public void setSelection(AbstractAttributeNode node) {
		StructuredSelection selection = new StructuredSelection(node);
		treeViewer.setSelection(selection);
	}
	
	public void refresh() {
		treeViewer.refresh();
	}
}
