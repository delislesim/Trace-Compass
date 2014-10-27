package org.eclipse.tracecompass.tmf.attributetree.ui.views;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AbstractAttributeNode;

public class AttributeTreeContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof AbstractAttributeNode) {
			AbstractAttributeNode node = (AbstractAttributeNode) parentElement;
			return node.getChildren().toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof AbstractAttributeNode) {
			return ((AbstractAttributeNode) element).getParent();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof AbstractAttributeNode) {
			return ((AbstractAttributeNode) element).hasChildren();
		}
		return false;
	}

}
