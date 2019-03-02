package utils.datastructures.stack.standard;

import utils.datastructures.stack.core.SinglyLinkedNode;

/**
 * Standard node for a standard stack implementation.
 * 
 * @author Riley McCuen
 */

public class StandardNode<E> implements SinglyLinkedNode<E, StandardNode<E>> {

	private StandardNode<E> next;

	private E data;

	public StandardNode(E data) {
		this.data = data;
	}

	@Override
	public StandardNode<E> getNext() {
		return next;
	}

	public void setNext(StandardNode<E> next) {
		this.next = next;
	}

	@Override
	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

}
