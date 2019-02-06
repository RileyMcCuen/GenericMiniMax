package utils.datastructures.stack;

public class SinglyLinkedNode<E> {

	private SinglyLinkedNode<E> next;

	private E value;

	public SinglyLinkedNode(E value) {
		this.value = value;
	}

	public SinglyLinkedNode<E> getNext() {
		return next;
	}

	public void setNext(SinglyLinkedNode<E> next) {
		this.next = next;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

}
