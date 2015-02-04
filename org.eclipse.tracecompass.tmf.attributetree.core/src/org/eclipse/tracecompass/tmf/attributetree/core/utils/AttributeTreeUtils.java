package org.eclipse.tracecompass.tmf.attributetree.core.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.tracecompass.tmf.attributetree.core.Activator;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.xml.sax.SAXException;

public class AttributeTreeUtils {
	
	private static Preferences preferences;
	
    private static final String ATTRIBUTE_TREE_XSD = "attributeTreeDefinition.xsd"; //$NON-NLS-1$
	
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
    	try {
			preferences.flush();
		} catch (BackingStoreException e) {
			// TODO Make something
		}
    }
    
    public static boolean attributeTreeXmlValidate(File xmlFile) {
        URL url = AttributeTreeUtils.class.getResource(ATTRIBUTE_TREE_XSD);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source xmlSource = new StreamSource(xmlFile);
        Schema schema;
		try {
			schema = schemaFactory.newSchema(url);
	        Validator validator = schema.newValidator();
	        validator.validate(xmlSource);
		} catch (SAXException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
    	return true;
    }

}
