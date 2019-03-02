package utils.datastructures.doublylinkedlist.standard;

import utils.datastructures.doublylinkedlist.core.DoublyLinkedListNode;

/**
 * Standard doubly linked list node for the standard list implementation.
 * 
 * @author Riley McCuen
 *
 */

public class StandardNode<V> extends DoublyLinkedListNode<V, StandardNode<V>> {

	public StandardNode(V data) {
		this.data = data;
	}

	@Override
	public V getData() {
		return data;
	}

}
