package utils.datastructures.testing;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

	private final int capacity;
    private LinkedHashMap<Integer, Double> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<Integer, Double>(capacity, 1, true) {
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
