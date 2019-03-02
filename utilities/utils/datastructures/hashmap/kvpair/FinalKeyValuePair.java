package utils.datastructures.hashmap.kvpair;

/**
 * Key value pair object with all fields final.
 * 
 * @author Riley McCuen
 *
 */

public class FinalKeyValuePair<K, V> {

	final private K key;

	final private V value;

	public FinalKeyValuePair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
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
		if (!(this.key.getClass().isInstance(obj))) {
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
