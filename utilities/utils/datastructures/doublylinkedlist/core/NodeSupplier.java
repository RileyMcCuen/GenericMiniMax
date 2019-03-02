package utils.datastructures.doublylinkedlist.core;

/**
 * Interface to provide constructor for special nodes to DoublyLinkedList
 * 
 * @author Riley McCuen
 *
 */

public interface NodeSupplier<V, D extends DoublyLinkedListNode<V, D>> {
	public D getNode(V value);
}
