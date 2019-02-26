package utils.datastructures.hashmap;

import java.util.ArrayList;

public class StaticSizeHashMap<K extends Object, V> {
	
	private int numBuckets, size;
	
	private final ArrayList<KVList<K,V>> buckets;
	
	public StaticSizeHashMap(int numBuckets) {
		this.numBuckets = numBuckets;
		this.size = 0;
		this.buckets = new ArrayList<>(numBuckets);
		for(int i = 0; i < numBuckets; ++i) {
			this.buckets.add(new KVList<>());
		}
	}
	
	public boolean empty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean contains(K key) {
		if(key == null) {
			return false;
		}
		int index = Math.floorMod(key.hashCode(), numBuckets);
		KVList<K,V> ls = buckets.get(index);
		for(KVNode<K, V> node : ls) {
			if(node.equals(key)) {
				return true;
			}
		}
		return false;
	}
	
	public V put(K key, V value) {
		if(key == null) {
			return null;
		}
		int index = Math.floorMod(key.hashCode(), numBuckets);
		KVList<K,V> ls = buckets.get(index);
		for(KVNode<K, V> node : ls) {
			if(node.equals(key)) {
				node.setValue(value);
				return value;
			}
		}
		++size;
		ls.addLast(new KeyValuePair<K, V>(key, value));
		return value;
	}
	
	public V getOrDefault(K key, V def) {
		if(key == null) {
			return null;
		}
		int index = Math.floorMod(key.hashCode(), numBuckets);
		KVList<K,V> ls = buckets.get(index);
		for(KVNode<K, V> node : ls) {
			if(node.equals(key)) {
				return node.getValue();
			}
		}
		return def;
	}
	
	public V remove(K key) {
		if(key == null || empty()) {
			return null;
		}
		int index = Math.floorMod(key.hashCode(), numBuckets);
		KVNode<K,V> foundNode = null;
		KVList<K,V> ls = buckets.get(index);
		for(KVNode<K, V> node : ls) {
			if(node.equals(key)) {
				foundNode = node;
				break;
			}
		}
		if(foundNode != null) {
			ls.deleteFromList(foundNode);
			--size;
			return foundNode.getValue();
		}
		return null;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Size of map: ");
		sb.append(size);
		sb.append(", Map contents: \n");
		for(KVList<K,V> ls : buckets) {
			sb.append("Bucket: \n");
			for(KVNode<K, V> it : ls) {
				sb.append(it.toString());
				sb.append(" ");
			}
			sb.append("\n\n");
		}
		sb.append("End of Map Contents \n");
		return sb.toString();
	}

}
