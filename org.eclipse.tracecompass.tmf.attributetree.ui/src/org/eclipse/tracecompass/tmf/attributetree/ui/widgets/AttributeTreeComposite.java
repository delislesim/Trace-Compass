package org.eclipse.tracecompass.tmf.attributetree.ui.widgets;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AbstractAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeTreePath;
import org.eclipse.tracecompass.tmf.attributetree.core.model.AttributeValueNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.ConstantAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.core.model.VariableAttributeNode;
import org.eclipse.tracecompass.tmf.attributetree.ui.views.AttributeTreeContentProvider;
import org.eclipse.tracecompass.tmf.attributetree.ui.views.AttributeTreeLabelProvider;
import org.eclipse.tracecompass.tmf.core.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AttributeTreeComposite extends Composite {
	
	private TreeViewer treeViewer;
	
	private AbstractAttributeNode invisibleRoot; // Hack pour voir la racine
	
	private Stack<Pair<AbstractAttributeNode, String>> queryNodeStack = new Stack<>();

	public AttributeTreeComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		
		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		
		treeViewer.getTree().setLayout(new GridLayout(1, false));
		GridData gridData = new GridData();
		gridData.horizontalSpan = 5;
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.verticalAlignment = SWT.FILL;
        gridData.grabExcessVerticalSpace = true;
        treeViewer.getTree().setLayoutData(gridData);
        
        treeViewer.setAutoExpandLevel(3);
		//treeViewer.setAutoExpandLevel(TreeViewer.ALL_LEVELS);
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
		return invisibleRoot;
	}
	
	public TreeViewer getTreeViewer() {
		return treeViewer;
	}
	
	public void setTreeViewerInput(File xmlTree) {
		if (xmlTree != null && xmlTree.exists()) {
			loadXmlTree(xmlTree);
		} else {
			invisibleRoot = new ConstantAttributeNode(null, "root");
		}
		
		treeViewer.setInput(invisibleRoot);
	}
	
	public IStructuredSelection getSelection() {
		return (IStructuredSelection) treeViewer.getSelection();
	}
	
	public void refresh() {
		treeViewer.refresh();
	}
	
	private void loadXmlTree(File xmlFile) {
		Document xmlTree = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			xmlTree = dBuilder.parse(xmlFile);
			xmlTree.getDocumentElement().normalize();
		} catch (ParserConfigurationException | SAXException | IOException e) {
		}
		
		NodeList nodeList = xmlTree.getElementsByTagName("root");
		Node xmlRootNode = nodeList.item(0); // Only one root
		invisibleRoot = new ConstantAttributeNode(null, ((Element)xmlRootNode).getAttribute("name"));
		getTreeFromXml(xmlRootNode, invisibleRoot);
		addQueryToTree();
	}
	
	private void getTreeFromXml(Node parentNode, AbstractAttributeNode parentAttribute) {
		NodeList childrenNodes = parentNode.getChildNodes();
		for(int i = 0; i < childrenNodes.getLength(); i++) {
			Node childNode = childrenNodes.item(i);
			if(childNode.getNodeType() == Node.TEXT_NODE) {
				continue;
			}
			Element childElement = (Element) childNode;
			AbstractAttributeNode parent = null;
			if (childElement.getAttribute("type").equals(ConstantAttributeNode.class.getSimpleName())) {
				parent = new ConstantAttributeNode(parentAttribute, childElement.getAttribute("name"));
			} else if (childElement.getAttribute("type").equals(VariableAttributeNode.class.getSimpleName())) {
				parent = new VariableAttributeNode(parentAttribute, childElement.getAttribute("name"));
				
				String xpathQuery = childElement.getAttribute("query");
				if(!xpathQuery.equals("")) {
					queryNodeStack.push(new Pair<AbstractAttributeNode, String>(parent, xpathQuery));
				}
			} else if (childElement.getAttribute("type").equals(AttributeValueNode.class.getSimpleName())) {
				parent = new AttributeValueNode(parentAttribute, childElement.getAttribute("name"));
			}
			getTreeFromXml(childNode, parent);
		}
	}
	
	private void addQueryToTree() {
		while (!queryNodeStack.empty()) {
			Pair<AbstractAttributeNode, String> queryPair = queryNodeStack.pop();
			VariableAttributeNode node = (VariableAttributeNode) queryPair.getFirst();
			String path = queryPair.getSecond();

			String[] splitedPath = path.split("/");
			AbstractAttributeNode currentNode = invisibleRoot;
			for (int i = 2; i < splitedPath.length; i++) { // Skip root + empty string
				currentNode = searchNode(currentNode, splitedPath[i]);
			}
			node.setIsQuery(true);
			node.setQueryPath(new AttributeTreePath(currentNode));
		}
	}
	
	private AbstractAttributeNode searchNode(AbstractAttributeNode parent, String nodeName) {
		if(!parent.hasChildren()) {
			return parent;
		}
		
		for(AbstractAttributeNode child : parent.getChildren()) {
			if(child.getName().replace(" ", "").equals(nodeName)) {
				return child;
			}
		}
		
		return null;
	}
}
