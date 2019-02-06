package utils.datastructures;

import utils.implementation.AbstractMove;

/**
 * Bare-bones stack data structure. Used to store move paths from previous
 * searches.
 * 
 * @author Riley McCuen
 *
 * @param <M> - The type of move to store in the stack.
 */
public class Stack<M extends AbstractMove> {

	private SinglyLinkedNode<M> top;

	public Stack() {
		this.top = null;
	}

	public Stack(SinglyLinkedNode<M> top) {
		this.top = top;
	}

	public boolean isEmpty() {
		return top == null;
	}

	public SinglyLinkedNode<M> pop() {
		SinglyLinkedNode<M> oldTop = top;
		top = top.getNext();
		oldTop.setNext(null);
		return oldTop;
	}

	public void push(M value) {
		SinglyLinkedNode<M> newTop = new SinglyLinkedNode<M>(value);
		newTop.setNext(top);
		top = newTop;
	}

}
