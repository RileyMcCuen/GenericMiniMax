package utils.datastructures.hashmap;

import utils.datastructures.doublylinkedlist.DoublyLinkedList;

public class KVList<K, V> extends DoublyLinkedList<KeyValuePair<K, V>, KVNode<K,V>>{

	public KVList() {
		super(value -> new KVNode<K,V>(value));
	}

}
