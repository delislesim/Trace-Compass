package org.eclipse.tracecompass.tmf.attributetree.core;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class Activator extends Plugin {

    /** The plug-in ID */
    public static final String PLUGIN_ID = "org.eclipse.tracecompass.tmf.attributetree.core"; //$NON-NLS-1$

    // The shared instance
    private static Activator fPlugin;

    /**
     * The constructor
     */
    public Activator() {
        setDefault(this);
    }

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        setDefault(this);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        setDefault(null);
        super.stop(context);
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
        return fPlugin;
    }

    // Sets plug-in instance
    private static void setDefault(Activator plugin) {
        fPlugin = plugin;
    }
}
    
//public class Activator implements BundleActivator {
//
//	private static BundleContext context;
//
//	static BundleContext getContext() {
//		return context;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
//	 */
//	public void start(BundleContext bundleContext) throws Exception {
//		Activator.context = bundleContext;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
//	 */
//	public void stop(BundleContext bundleContext) throws Exception {
//		Activator.context = null;
//	}
//
//}
