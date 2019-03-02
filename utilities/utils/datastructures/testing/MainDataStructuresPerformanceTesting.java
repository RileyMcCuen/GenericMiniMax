package utils.datastructures.testing;

import java.util.HashMap;
import java.util.Random;

import utils.datastructures.doublylinkedlist.core.DoublyLinkedList;
import utils.datastructures.doublylinkedlist.kv.KVNode;
import utils.datastructures.hashmap.kvpair.KeyValuePair;
import utils.datastructures.hashmap.wlist.StaticListHashMap;
import utils.datastructures.hashmap.wstack.StaticStackHashMap;
import utils.datastructures.lrucache.LRUMap;

/**
 * Class for rough estimates and comparisons before attempting a full JMH
 * benchmark.
 * 
 * @author Riley McCuen
 */

public class MainDataStructuresPerformanceTesting {

	private static int SIZE = 100000;
	private static int ITER = 50;
	private static int NANO = 1000000;

	public static void main(String[] args) {
		long mapTime = 0;
		long cacheTime = 0;
		for (int i = 0; i < 100; ++i) {
			LRUCache c = new LRUCache(SIZE);
			long time = System.nanoTime();
			for (int j = 0; j < 5 * SIZE; ++j) {
				c.put(j, Double.valueOf(j));
			}
			if (i >= ITER) {
				cacheTime += System.nanoTime() - time;
			}
			LRUMap<Integer, Double> map = new LRUMap<Integer, Double>(SIZE, SIZE);
			time = System.nanoTime();
			for (int j = 0; j < 5 * SIZE; ++j) {
				map.put(j, Double.valueOf(j));
			}
			if (i >= ITER) {
				mapTime += System.nanoTime() - time;
			}
			System.out.println(i);
		}
		System.out.println("MapTime: " + (((double) mapTime) / (NANO * ITER)) + ", CacheTime: "
				+ (((double) cacheTime) / (NANO * ITER)));
		mapTime = 0;
		cacheTime = 0;
		for (int i = 0; i < 100; ++i) {
			StaticStackHashMap<Integer, Double> map = new StaticStackHashMap<Integer, Double>(4 * SIZE);
			long time = System.nanoTime();
			for (int j = 0; j < 5 * SIZE; ++j) {
				map.put(j, Double.valueOf(j));
			}
			if (i >= ITER) {
				mapTime += System.nanoTime() - time;
			}
			HashMap<Integer, Double> c = new HashMap<Integer, Double>(SIZE);
			time = System.nanoTime();
			for (int j = 0; j < 5 * SIZE; ++j) {
				c.put(j, Double.valueOf(j));
			}
			if (i >= ITER) {
				cacheTime += System.nanoTime() - time;
			}
			System.out.println(i);
		}
		System.out.println("MapTime: " + ((double) mapTime / ITER) + ", CacheTime: " + ((double) cacheTime / ITER));
	}

}
