package utils.datastructures.hashmap.wstack;

import utils.datastructures.hashmap.kvpair.FinalKeyValuePair;
import utils.datastructures.stack.fkv.FKVStack;

/**
 * Static sized hash map that only allows inserting new elements. Attempted to
 * create this map to outperform standard Java HashMap. After extensive testing
 * using JMH benchmarks the Java HashMap is substantially faster. This map uses
 * final stacks to implement buckets.
 * 
 * @author Riley McCuen
 *
 */

public class StaticStackHashMap<K, V> {

	private int numBuckets, size;

	private final FKVStack<K, V>[] buckets;

	@SuppressWarnings("unchecked")
	public StaticStackHashMap(int numBuckets) {
		this.numBuckets = numBuckets;
		this.size = 0;
		this.buckets = new FKVStack[numBuckets];
		for (int i = 0; i < numBuckets; ++i) {
			this.buckets[i] = new FKVStack<>();
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
		for (FinalKeyValuePair<K, V> kvPair : buckets[index]) {
			if (kvPair.hashCode() == hash && kvPair.equals(key)) {
				return true;
			}
		}
		return false;
	}

	public V put(K key, V value) {
		if (key == null) {
			return null;
		}
		int index = Math.floorMod(key.hashCode(), numBuckets);
		buckets[index].push(key, value);
		++size;
		return value;
	}

	public V getOrDefault(K key, V def) {
		if (key == null) {
			return def;
		}
		int hash = key.hashCode();
		int index = Math.floorMod(key.hashCode(), numBuckets);
		for (FinalKeyValuePair<K, V> kvPair : buckets[index]) {
			if (kvPair.hashCode() == hash && kvPair.equals(key)) {
				kvPair.getValue();
			}
		}
		return def;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Size of map: ");
		sb.append(size);
		sb.append(", Map contents: \n");
		for (FKVStack<K, V> s : buckets) {
			sb.append("Bucket: \n");
			for (FinalKeyValuePair<K, V> it : s) {
				sb.append(it.toString());
				sb.append(" ");
			}
			sb.append("\n\n");
		}
		sb.append("End of Map Contents \n");
		return sb.toString();
	}

}
