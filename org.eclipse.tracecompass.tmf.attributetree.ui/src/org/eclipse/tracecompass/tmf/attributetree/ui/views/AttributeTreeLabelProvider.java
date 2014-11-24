package org.eclipse.tracecompass.tmf.attributetree.ui.views;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AbstractAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeValueNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.ConstantAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.VariableAttributeNode;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class AttributeTreeLabelProvider implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
		//TODO : dispose image
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	public Image getImage(Object element) {
		Bundle bundle = FrameworkUtil.getBundle(AttributeTreeLabelProvider.class);
		URL url = null;
		if(element instanceof ConstantAttributeNode) {
			url = FileLocator.find(bundle, new Path("icons/constantAttribute.png"), null);
		} else if(element instanceof VariableAttributeNode) {
			url = FileLocator.find(bundle, new Path("icons/variableAttribute.png"), null);
		} else if(element instanceof AttributeValueNode) {
			url = FileLocator.find(bundle, new Path("icons/value.png"), null);
		}
		
		return ImageDescriptor.createFromURL(url).createImage();
	}

	@Override
	public String getText(Object element) {
		if (element instanceof VariableAttributeNode) {
			if(((VariableAttributeNode) element).getIsQuery()) {
				return "$ " + ((VariableAttributeNode) element).getName() + " (" + ((VariableAttributeNode) element).getQueryPath().getPathFromAttributeTreePath() + ")";
			}
			return "$ " + ((VariableAttributeNode) element).getName();
		} else if (element instanceof AbstractAttributeNode) {
			return ((AbstractAttributeNode) element).getName();
		}
		return null;
	}
}
