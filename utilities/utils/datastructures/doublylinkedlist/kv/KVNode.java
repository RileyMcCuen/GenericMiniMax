package utils.datastructures.doublylinkedlist.kv;

import utils.datastructures.doublylinkedlist.core.DoublyLinkedListNode;
import utils.datastructures.hashmap.kvpair.KeyValuePair;

/**
 * Key Value node used in the Key value list.
 * 
 * @author Riley McCuen
 *
 */
public class KVNode<K, V> extends DoublyLinkedListNode<KeyValuePair<K, V>, KVNode<K, V>> {

	private int hash;

	public KVNode(KeyValuePair<K, V> kvPair) {
		this.data = kvPair;
		hash = kvPair.hashCode();
	}

	public K getKey() {
		return data.getKey();
	}

	public V getValue() {
		return data.getValue();
	}

	public void setValue(V value) {
		data.setValue(value);
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		return data.equals(obj);
	}

	@Override
	public String toString() {
		return data.toString();
	}

}
