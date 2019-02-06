package utils.datastructures.stack;

import java.util.Iterator;

import utils.implementation.AbstractMove;

public class StackIterator<M extends AbstractMove> implements Iterator<M> {

	private final Stack<M> backingStack;

	public StackIterator(Stack<M> backingStack) {
		this.backingStack = backingStack;
	}

	@Override
	public boolean hasNext() {
		return !backingStack.isEmpty();
	}

	@Override
	public M next() {
		return backingStack.popMove();
	}

}
