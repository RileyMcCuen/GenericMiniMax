package utils.datastructures.testing;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implementation of LRUCache using Java's LinkedHashMap class.
 * 
 * @author Riley McCuen
 *
 */

public class LRUCache {

	private LinkedHashMap<Integer, Double> map;

	public LRUCache(int capacity) {
		this.map = new LinkedHashMap<Integer, Double>(capacity, 1, true) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(Map.Entry<Integer, Double> eldest) {
				return map.size() > capacity;
			}
		};
	}

	public Double getOrDefault(Object k, Double def) {
		return map.getOrDefault(k, def);
	}

	public Double put(Integer key, Double value) {
		return map.put(key, value);
	}
}
