package org.eclipse.tracecompass.tmf.xmlconverter.core;

import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeListIterable implements Iterable<Node> {
	
	private NodeList nodeList;
	private int currentSize;
	
	public NodeListIterable(NodeList list) {
		nodeList = list;
		currentSize = nodeList.getLength();
	}

	@Override
	public Iterator<Node> iterator() {
		return new Iterator<Node>() {

			private int currentIndex = 0;
			
			@Override
			public boolean hasNext() {
				return currentIndex < currentSize;
			}

			@Override
			public Node next() {
				Node currentNode = null;
				for(; currentIndex < currentSize; currentIndex++) {
					if(nodeList.item(currentIndex).getAttributes() != null) {
						currentNode = nodeList.item(currentIndex);
						++currentIndex;
						return currentNode;
					}
				}
				return currentNode;
			}

			@Override
			public void remove() {
				// You can't remove items in nodeList
			}
			
		};
	}

}
