package utils.datastructures.stack;

import java.util.Iterator;

import utils.implementation.core.AbstractMove;

/**
 * Bare-bones stack data structure. Used to store move paths from previous
 * searches.
 * 
 * @author Riley McCuen
 *
 * @param <M> - The type of move to store in the stack.
 */
public class Stack<M extends AbstractMove> implements Iterable<M> {

	private SinglyLinkedNode<M> top;

	public Stack() {
		this.top = null;
	}

	public Stack(SinglyLinkedNode<M> top) {
		this.top = top;
	}

	public Stack(M top) {
		this.top = new SinglyLinkedNode<M>(top);
	}

	public boolean isEmpty() {
		return top == null;
	}

	public M popMove() {
		if (isEmpty()) {
			return null;
		}
		return popNode().getValue();
	}

	public SinglyLinkedNode<M> popNode() {
		if (isEmpty()) {
			return null;
		}
		SinglyLinkedNode<M> oldTop = top;
		top = top.getNext();
		oldTop.setNext(null);
		return oldTop;
	}

	public M peekMove() {
		if (isEmpty()) {
			return null;
		}
		return peekNode().getValue();
	}

	public SinglyLinkedNode<M> peekNode() {
		return top;
	}

	public void pushMove(M value) {
		pushNode(new SinglyLinkedNode<M>(value));
	}

	public void pushNode(SinglyLinkedNode<M> newTop) {
		newTop.setNext(top);
		top = newTop;
	}

	public void swapStack(Stack<M> stack) {
		if (stack.isEmpty()) {
			return;
		}
		top = stack.peekNode();
	}

	@Override
	public Iterator<M> iterator() {
		return new StackIterator<M>(this);
	}

}
