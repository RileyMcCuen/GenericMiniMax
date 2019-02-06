package utils.datastructures;

public class DoublyLinkedListNode<E> {

	private DoublyLinkedListNode<E> prev, next;

	private E value;

	public DoublyLinkedListNode(E value) {
		this.prev = null;
		this.next = null;
		this.value = value;
	}

	public DoublyLinkedListNode<E> getPrev() {
		return prev;
	}

	public void setPrev(DoublyLinkedListNode<E> prev) {
		this.prev = prev;
	}

	public DoublyLinkedListNode<E> getNext() {
		return next;
	}

	public void setNext(DoublyLinkedListNode<E> next) {
		this.next = next;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

}
