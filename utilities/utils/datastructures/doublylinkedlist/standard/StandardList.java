package utils.datastructures.doublylinkedlist.standard;

import utils.datastructures.doublylinkedlist.core.DoublyLinkedList;

/**
 * Standard doubly linked list implementation.
 * 
 * @author Riley McCuen
 *
 */

public class StandardList<V> extends DoublyLinkedList<V, StandardNode<V>> {

	public StandardList() {
		super(value -> new StandardNode<V>(value));
	}
}
