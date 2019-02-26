package utils.datastructures.doublylinkedlist;

public class StandardList<V> extends DoublyLinkedList<V, StandardNode<V>>{

	public StandardList() {
		super(value -> new StandardNode<V>(value));
	}
}
