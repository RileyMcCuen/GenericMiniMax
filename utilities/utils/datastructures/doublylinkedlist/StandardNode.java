package utils.datastructures.doublylinkedlist;

public class StandardNode<V> extends DoublyLinkedListNode<V, StandardNode<V>> {
	
	public StandardNode(V data) {
		this.data = data;
	}
	
	@Override
	public V getData() {
		return data;
	}

}
