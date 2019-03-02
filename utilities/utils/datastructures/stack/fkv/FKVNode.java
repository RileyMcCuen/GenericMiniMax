package utils.datastructures.stack.fkv;

import utils.datastructures.hashmap.kvpair.FinalKeyValuePair;
import utils.datastructures.stack.core.SinglyLinkedNode;

/**
 * Node class similar to the final node that stores a final key value pair but
 * next is not locked to final.
 * 
 * @author Riley McCuen
 */

public class FKVNode<K, V> implements SinglyLinkedNode<FinalKeyValuePair<K, V>, FKVNode<K, V>> {

	final protected K key;

	final protected V value;

	final protected int keyHash;

	protected FKVNode<K, V> next;

	public FKVNode(FinalKeyValuePair<K, V> kvPair, FKVNode<K, V> next) {
		this(kvPair.getKey(), kvPair.getValue(), next);
	}

	public FKVNode(K key, V value, FKVNode<K, V> next) {
		this.key = key;
		this.value = value;
		this.keyHash = key.hashCode();
		this.next = next;
	}

	@Override
	public FKVNode<K, V> getNext() {
		return next;
	}

	public void setNext(FKVNode<K, V> node) {
		this.next = node;
	}

	@Override
	public FinalKeyValuePair<K, V> getData() {
		return new FinalKeyValuePair<K, V>(key, value);
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return keyHash;
	}

}
