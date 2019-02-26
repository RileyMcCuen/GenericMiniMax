package utils.datastructures.testing;

import utils.datastructures.doublylinkedlist.DoublyLinkedList;
import utils.datastructures.hashmap.KVNode;
import utils.datastructures.hashmap.KeyValuePair;
import utils.datastructures.hashmap.StaticSizeHashMap;

public class MainDataStructuresPerformanceTesting {

	public static void main(String[] args) {
		StaticSizeHashMap<Integer, Double> map = new StaticSizeHashMap<Integer, Double>(3); 
		for(int i = 0; i < 10; ++i) {
			map.put(i, Double.valueOf(100 - i));
			System.out.println(map.toString());
		}
		for(int i = 0; i < 10; ++i) {
			map.remove(i);
			System.out.println(map.toString());
		}
	}

}
