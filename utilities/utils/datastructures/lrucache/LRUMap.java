package utils.datastructures.lrucache;

import utils.datastructures.doublylinkedlist.kv.KVList;
import utils.datastructures.doublylinkedlist.kv.KVNode;
import utils.datastructures.hashmap.kvpair.KeyValuePair;
import utils.datastructures.hashmap.wlist.StaticListHashMap;

/**
 * LRUCache data structure designed to be used in transposition tables. After
 * extensive testing using JMH benchmarks Java's standard LinkedHashMap
 * implementation is far superior.
 * 
 * @author Riley McCuen
 *
 */
public class LRUMap<K, V> {

	private static final int DEFAULT_MAX_SIZE = 1000000;
	private static final float DEFAULT_LOAD_FACTOR = 0.25f;
	private static final float MAX_LOAD_FACTOR = 1000f;
	private static final float MIN_LOAD_FACTOR = 0.0001f;

	private final StaticListHashMap<K, KVNode<K, V>> map;
	private final KVList<K, V> list = new KVList<>();
	private final int maxSize;

	public LRUMap(float loadFactor, int maxSize) {
		loadFactor = Float.max(loadFactor, MIN_LOAD_FACTOR);
		loadFactor = Float.min(loadFactor, MAX_LOAD_FACTOR);
		this.maxSize = Integer.max(maxSize, 1);
		this.map = new StaticListHashMap<>(Math.round(maxSize / loadFactor));
	}

	public LRUMap(int size, int maxSize) {
		size = Integer.max(size, 1);
		this.maxSize = Integer.max(maxSize, 1);
		this.map = new StaticListHashMap<>(Math.round(size / DEFAULT_LOAD_FACTOR));
	}

	public LRUMap(int maxSize) {
		this.maxSize = Integer.max(maxSize, 1);
		this.map = new StaticListHashMap<>(Math.round(maxSize / DEFAULT_LOAD_FACTOR));
	}

	public LRUMap() {
		this.maxSize = DEFAULT_MAX_SIZE;
		this.map = new StaticListHashMap<>(Math.round(DEFAULT_MAX_SIZE / DEFAULT_LOAD_FACTOR));
	}

	public int size() {
		return map.size();
	}

	public V put(K key, V value) {
		KVNode<K, V> node = map.getOrDefault(key, null);
		if (node != null) {
			node.setValue(value);
			list.updateNode(node);
			return value;
		} else {
			if (map.size() >= maxSize) {
				map.remove(list.deleteHead().getKey());
			}
			KVNode<K, V> newNode = new KVNode<>(new KeyValuePair<K, V>(key, value));
			list.addLast(newNode);
			map.put(key, newNode);
			return value;
		}
	}

	public V getOrDefault(K key, V def) {
		KVNode<K, V> ret = map.getOrDefault(key, null);
		if (ret == null) {
			return def;
		} else {
			list.updateNode(ret);
			return ret.getValue();
		}
	}

	public String toString() {
		return list.toString();
	}

}
