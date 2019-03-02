package utils.datastructures.doublylinkedlist.kv;

import utils.datastructures.doublylinkedlist.core.DoublyLinkedList;
import utils.datastructures.hashmap.kvpair.KeyValuePair;

/**
 * Key Value list that can be used in hashmap data structures as buckets.
 * 
 * @author Riley McCuen
 *
 */

public class KVList<K, V> extends DoublyLinkedList<KeyValuePair<K, V>, KVNode<K, V>> {

	public KVList() {
		super(value -> new KVNode<K, V>(value));
	}

}
