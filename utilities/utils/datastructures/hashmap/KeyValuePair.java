package utils.datastructures.hashmap;

public class KeyValuePair<K, V> extends Object {
	
	private final K key;
	private V value;
	
	public KeyValuePair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}
	
	public void setValue(V value) {
		this.value = value;
	}

	public V getValue() {
		return value;
	}
	
	@Override
	public int hashCode() {
		return key.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(this.key.getClass().isInstance(obj))) {
			return false;
		} else {
			return this.key.equals(obj);
		}
	}
	
	@Override
	public String toString() {
		return value.toString();
	}

}
