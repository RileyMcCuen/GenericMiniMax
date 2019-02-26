package utils.datastructures.doublylinkedlist;

public class DoublyLinkedListNode<V, D extends DoublyLinkedListNode<V, D>> {
	
	private D prev, next;
	
	protected V data;
	
	public D getNext() {
		return next;
	}
	public void setNext(D node) {
		this.next = node;
	}
	public D getPrev() {
		return this.prev;
	}
	public void setPrev(D node) {
		this.prev = node;
	}
	public V getData() {
		return data;
	}

}
