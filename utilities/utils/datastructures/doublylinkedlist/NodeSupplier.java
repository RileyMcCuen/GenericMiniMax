package utils.datastructures.doublylinkedlist;

public interface NodeSupplier<V, D extends DoublyLinkedListNode<V, D>> {
	public D getNode(V value);
}
