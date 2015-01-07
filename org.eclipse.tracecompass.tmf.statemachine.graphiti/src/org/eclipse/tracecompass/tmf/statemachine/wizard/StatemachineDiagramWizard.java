package org.eclipse.tracecompass.tmf.statemachine.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

public class StatemachineDiagramWizard extends BasicNewResourceWizard {
	
	private static final String PAGE_NAME_DIAGRAM_NAME = "Diagram Name";
	private static final String WIZARD_WINDOW_TITLE = "New Diagram";

	private Diagram diagram;
	private String diagramTypeId = "org.eclipse.tracecompass.tmf.statemachine.graphiti.StateMachineDiagramType";
	
	private StatemachineDiagramPage diagramNamePage;
	
	@Override
	public void addPages() {
		super.addPages();
		diagramNamePage = new StatemachineDiagramPage(PAGE_NAME_DIAGRAM_NAME);
		addPage(diagramNamePage);
	}
	
	@Override
	public boolean canFinish() {
		return super.canFinish();
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);
		setWindowTitle(WIZARD_WINDOW_TITLE);
	}

	@Override
	public boolean performFinish() {
		String diagramName = diagramNamePage.getDiagramName();
		
		IProject project = null;
		IFolder diagramFolder = null;
		
		Object element = getSelection().getFirstElement();
		if (element instanceof IProject) {
			project = (IProject) element;
		} else if (element instanceof IFolder) {
			diagramFolder = (IFolder) element;
			project = diagramFolder.getProject();
		}
		
		if (project == null || !project.isAccessible()) {
			return false;
		}
		
		Diagram diagram = Graphiti.getPeCreateService().createDiagram(diagramTypeId, diagramName, true);
		if (diagramFolder == null) {
			diagramFolder = project.getFolder("Diagrams/");
		}
		
		String statemachineDiagramExtension = "diagram";
		IFile diagramFile = diagramFolder.getFile(diagramName + "." + statemachineDiagramExtension);
		
		return true;
//		StatemachineDiagramPage namePage = (StatemachineDiagramPage) getPage(PAGE_NAME_DIAGRAM_NAME);
//		final String diagramName = namePage.getDiagramName();
//
//		IProject project = null;
//		IFolder diagramFolder = null;
//
//		Object element = getSelection().getFirstElement();
//		if (element instanceof IProject) {
//			project = (IProject) element;
//		} else if (element instanceof IFolder) {
//			diagramFolder = (IFolder) element;
//			project = diagramFolder.getProject();
//		}
//
//		/*if (project == null || !project.isAccessible()) {
//			String error = "Select a project and restart the wizard.";
//			IStatus status = new Status(IStatus.ERROR, ExamplesCommonPlugin.getID(), error);
//			ErrorDialog.openError(getShell(), Messages.CreateDiagramWizard_NoProjectFoundErrorTitle, null, status);
//			return false;
//		}*/
//
//		Diagram diagram = Graphiti.getPeCreateService().createDiagram(diagramTypeId, diagramName, true);
//		if (diagramFolder == null) {
//			diagramFolder = project.getFolder("Diagrams/"); //$NON-NLS-1$
//		}
//
//		String editorID = DiagramEditor.DIAGRAM_EDITOR_ID;
//		String editorExtension = "diagram"; //$NON-NLS-1$
//		String diagramTypeProviderId = GraphitiUi.getExtensionManager().getDiagramTypeProviderId(diagramTypeId);
//		String namingConventionID = diagramTypeProviderId + ".editor"; //$NON-NLS-1$
//		IEditorDescriptor specificEditor = PlatformUI.getWorkbench().getEditorRegistry().findEditor(namingConventionID);
//
//		// If there is a specific editor get the file extension
//		if (specificEditor != null) {
//			editorID = namingConventionID;
//			IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
//			IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint("org.eclipse.ui.editors"); //$NON-NLS-1$
//			IExtension[] extensions = extensionPoint.getExtensions();
//			for (IExtension ext : extensions) {
//				IConfigurationElement[] configurationElements = ext.getConfigurationElements();
//				for (IConfigurationElement ce : configurationElements) {
//					String id = ce.getAttribute("id"); //$NON-NLS-1$
//					if (editorID.equals(id)) {
//						String fileExt = ce.getAttribute("extensions"); //$NON-NLS-1$
//						if (fileExt != null) {
//							editorExtension = fileExt;
//							break;
//						}
//					}
//
//				}
//			}
//		}
//
//		IFile diagramFile = diagramFolder.getFile(diagramName + "." + editorExtension); //$NON-NLS-1$
//		URI uri = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
//
//		FileService.createEmfFileForDiagram(uri, diagram);
//		String providerId = GraphitiUi.getExtensionManager().getDiagramTypeProviderId(diagram.getDiagramTypeId());
//		DiagramEditorInput editorInput = new DiagramEditorInput(EcoreUtil.getURI(diagram), providerId);
//		
//		try {
//			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(editorInput, editorID);
//		} catch (PartInitException e) {
//			String error = "Error while opening diagram editor";
//			IStatus status = new Status(IStatus.ERROR, "org.eclipse.linuxtools.tmf.statemachine.graphiti", error, e);
//			ErrorDialog.openError(getShell(), "An error occured", null, status);
//			return false;
//		}
//
//		return true;
	}

}
