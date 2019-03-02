package utils.datastructures.stack.standard;

import java.util.Iterator;

import utils.datastructures.stack.core.Stack;

/**
 * Standard stack implementation.
 * 
 * @author Riley McCuen
 */

public class StandardStack<E> implements Stack<E, StandardNode<E>> {

	private StandardNode<E> top;

	public StandardStack() {
		this.top = null;
	}

	public StandardStack(StandardNode<E> top) {
		this.top = top;
	}

	@Override
	public boolean empty() {
		return top == null;
	}

	@Override
	public E pop() {
		if (empty()) {
			return null;
		}
		StandardNode<E> oldTop = top;
		top = top.getNext();
		oldTop.setNext(null);
		return oldTop.getData();
	}

	@Override
	public E peek() {
		if (empty()) {
			return null;
		}
		return top.getData();
	}

	@Override
	public void push(E value) {
		StandardNode<E> newTop = new StandardNode<E>(value);
		newTop.setNext(top);
		top = newTop;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			StandardNode<E> node = top;

			@Override
			public boolean hasNext() {
				return !(empty() || node.getNext() == null);
			}

			@Override
			public E next() {
				return node.getNext().getData();
			}

		};
	}
}
