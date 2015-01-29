package org.eclipse.tracecompass.tmf.attributetree.core.utils;

import java.io.File;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.tracecompass.tmf.attributetree.core.Activator;
import org.osgi.service.prefs.Preferences;

public class AttributeTreeUtils {
	
	private static Preferences preferences;
	
    /** Make this class non-instantiable */
    private AttributeTreeUtils() {

    }
    
    public static File getAttributeTreeFile(String diagramName) {
    	if(preferences == null) {
    		preferences = InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID);
    	}
    	
    	String attributeTreeFilePath;
    	attributeTreeFilePath = preferences.get(diagramName, "");
    	File attributeTreeFile;
    	if(attributeTreeFilePath.equals("")) {
    		attributeTreeFile = null;
    	} else {
			attributeTreeFile = new File(attributeTreeFilePath);
		}
    	
    	return attributeTreeFile;
    }
    
    public static void addAttributeTreeFile(String diagramName, String filePath) {
    	if(preferences == null) {
    		preferences = InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID);
    	}
    	
    	preferences.put(diagramName, filePath);
    }

}
