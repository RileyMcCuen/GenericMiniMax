package utils.datastructures.stack.fkv;

import java.util.Iterator;

import utils.datastructures.hashmap.kvpair.FinalKeyValuePair;
import utils.datastructures.stack.core.Stack;

/**
 * Final key value stack that is backed by final key value nodes.
 * 
 * @author Riley McCuen
 */

public class FKVStack<K, V> implements Stack<FinalKeyValuePair<K, V>, FKVNode<K, V>> {

	private FKVNode<K, V> top;

	@Override
	public boolean empty() {
		return top == null;
	}

	@Override
	public FinalKeyValuePair<K, V> pop() {
		if (empty()) {
			return null;
		}
		FKVNode<K, V> oldTop = top;
		top = top.getNext();
		return oldTop.getData();
	}

	@Override
	public FinalKeyValuePair<K, V> peek() {
		if (empty()) {
			return null;
		}
		return top.getData();
	}

	@Override
	public void push(FinalKeyValuePair<K, V> kvPair) {
		FKVNode<K, V> newTop = new FKVNode<K, V>(kvPair, top);
		top = newTop;
	}

	public void push(K key, V value) {
		push(new FinalKeyValuePair<K, V>(key, value));
	}

	@Override
	public Iterator<FinalKeyValuePair<K, V>> iterator() {
		return new Iterator<FinalKeyValuePair<K, V>>() {
			FKVNode<K, V> node = top;

			@Override
			public boolean hasNext() {
				return !(empty() || node.getNext() == null);
			}

			@Override
			public FinalKeyValuePair<K, V> next() {
				return node.getNext().getData();
			}

		};
	}

}
