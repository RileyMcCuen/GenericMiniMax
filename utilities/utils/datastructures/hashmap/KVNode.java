package utils.datastructures.hashmap;

import utils.datastructures.doublylinkedlist.DoublyLinkedListNode;

public class KVNode<K, V> extends DoublyLinkedListNode<KeyValuePair<K,V>, KVNode<K,V>> {

	public KVNode(KeyValuePair<K, V> kvPair) {
		this.data = kvPair;
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
		return data.hashCode();
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
