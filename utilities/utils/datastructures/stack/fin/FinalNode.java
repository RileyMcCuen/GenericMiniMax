package utils.datastructures.stack.fin;

import utils.datastructures.stack.core.SinglyLinkedNode;

/**
 * Node class that has only final fields and is used by the final stack class.
 * 
 * @author Riley McCuen
 */

public class FinalNode<E> implements SinglyLinkedNode<E, FinalNode<E>> {

	final protected E data;

	protected final FinalNode<E> next;

	public FinalNode(E data, FinalNode<E> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public FinalNode<E> getNext() {
		return next;
	}

	@Override
	public E getData() {
		return data;
	}

}
