package org.eclipse.tracecompass.tmf.attributetree.core.utils;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.tracecompass.tmf.attributetree.core.Activator;

public class AttributeTreeXmlUtils {
	
	private static final String ATTRIBUTETREE_XML_DIRECTORY = "attributetree_xml_files";
	public static final String FILE_NAME = "attributetree.xml";
	
    /** Make this class non-instantiable */
    private AttributeTreeXmlUtils() {

    }
    
    public static IPath getTreeXmlFilesPath() {
    	IPath path = Activator.getDefault().getStateLocation();
        path = path.addTrailingSeparator().append(ATTRIBUTETREE_XML_DIRECTORY);

        /* Check if directory exists, otherwise create it */
        File dir = path.toFile();
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }

        return path;
    }
    
    public static void addAttributeTreeXmlFile(File file) {
    	File xmlFile = getTreeXmlFilesPath().addTrailingSeparator().append(file.getName()).toFile();
    	
		try {
			if (!xmlFile.exists()) {
				xmlFile.createNewFile();
			}
		} catch (IOException e) {
			// TODO Faire quelque chose
		}
		
		// TODO Continuer
    	
//    	/* Copy file to path */
//        File toFile = getXmlFilesPath().addTrailingSeparator().append(fromFile.getName()).toFile();
//
//        try {
//            if (!toFile.exists()) {
//                toFile.createNewFile();
//            }
//        } catch (IOException e) {
//            String error = Messages.XmlUtils_ErrorCopyingFile;
//            Activator.logError(error, e);
//            return new Status(IStatus.ERROR, Activator.PLUGIN_ID, error, e);
//        }
//
//        try (FileInputStream fis = new FileInputStream(fromFile);
//                FileOutputStream fos = new FileOutputStream(toFile);
//                FileChannel source = fis.getChannel();
//                FileChannel destination = fos.getChannel();) {
//            destination.transferFrom(source, 0, source.size());
//        } catch (IOException e) {
//            String error = Messages.XmlUtils_ErrorCopyingFile;
//            Activator.logError(error, e);
//            return new Status(IStatus.ERROR, Activator.PLUGIN_ID, error, e);
//        }
//        return Status.OK_STATUS;
    }

}
