package utils.datastructures.stack.fin;

import java.util.Iterator;

import utils.datastructures.stack.core.Stack;

/**
 * Stack backed by a node close with only final fields.
 * 
 * @author Riley McCuen
 */

public class FinalStack<E> implements Stack<E, FinalNode<E>> {

	private FinalNode<E> top;

	@Override
	public boolean empty() {
		return top == null;
	}

	@Override
	public E pop() {
		if (empty()) {
			return null;
		}
		FinalNode<E> oldTop = top;
		top = top.getNext();
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
		FinalNode<E> newTop = new FinalNode<E>(value, top);
		top = newTop;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			FinalNode<E> node = top;

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
