package utils.datastructures.hashmap.wlist;

import utils.datastructures.doublylinkedlist.kv.KVList;
import utils.datastructures.doublylinkedlist.kv.KVNode;
import utils.datastructures.hashmap.kvpair.KeyValuePair;

/**
 * Static sized hash map. Attempted to create this map to outperform standard
 * Java HashMap. After extensive testing using JMH benchmarks the Java HashMap
 * is substantially faster. This map uses doubly linked lists as buckets.
 * 
 * @author Riley McCuen
 *
 */
public class StaticListHashMap<K, V> {

	private int numBuckets, size;

	private final KVList<K, V>[] buckets;

	@SuppressWarnings("unchecked")
	public StaticListHashMap(int numBuckets) {
		this.numBuckets = numBuckets;
		this.size = 0;
		this.buckets = new KVList[numBuckets];
		for (int i = 0; i < numBuckets; ++i) {
			this.buckets[i] = new KVList<>();
		}
	}

	public boolean empty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public boolean contains(K key) {
		if (key == null) {
			return false;
		}
		int hash = key.hashCode();
		int index = Math.floorMod(hash, numBuckets);
		for (KVNode<K, V> node = buckets[index].getHead(); node != null; node = node.getNext()) {
			if (node.hashCode() == hash && node.equals(key)) {
				return true;
			}
		}
		return false;
	}

	public V put(K key, V value) {
		if (key == null) {
			return null;
		}
		int hash = key.hashCode();
		int index = Math.floorMod(key.hashCode(), numBuckets);
		KVList<K, V> ls = buckets[index];
		for (KVNode<K, V> node = ls.getHead(); node != null; node = node.getNext()) {
			if (node.hashCode() == hash && node.equals(key)) {
				node.setValue(value);
				return value;
			}
		}
		++size;
		ls.addLast(new KeyValuePair<K, V>(key, value));
		return value;
	}

	public V getOrDefault(K key, V def) {
		if (key == null) {
			return def;
		}
		int hash = key.hashCode();
		int index = Math.floorMod(key.hashCode(), numBuckets);
		for (KVNode<K, V> node = buckets[index].getHead(); node != null; node = node.getNext()) {
			if (node.hashCode() == hash && node.equals(key)) {
				node.getValue();
			}
		}
		return def;
	}

	public V remove(K key) {
		if (key == null || empty()) {
			return null;
		}
		int hash = key.hashCode();
		int index = Math.floorMod(key.hashCode(), numBuckets);
		KVList<K, V> ls = buckets[index];
		for (KVNode<K, V> node = ls.getHead(); node != null; node = node.getNext()) {
			if (node.hashCode() == hash && node.equals(key)) {
				ls.deleteFromList(node);
				--size;
				return node.getValue();
			}
		}
		return null;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Size of map: ");
		sb.append(size);
		sb.append(", Map contents: \n");
		for (KVList<K, V> ls : buckets) {
			sb.append("Bucket: \n");
			for (KVNode<K, V> it : ls) {
				sb.append(it.toString());
				sb.append(" ");
			}
			sb.append("\n\n");
		}
		sb.append("End of Map Contents \n");
		return sb.toString();
	}

}
