package utils.datastructures.testing;

import java.util.Stack;

import minimax.fourinarow.core.arrays.core.FourInARowMove;

public class MainDataStructuresPerformanceTesting {

	public static void main(String[] args) {
		Stack<FourInARowMove> myStack = new Stack<FourInARowMove>();
		long time = System.nanoTime();
		for (int i = 0; i < 10000000; i++) {
			myStack.push(new FourInARowMove(3));
		}
		time = System.nanoTime() - time;
		System.out.println("My stack finished in: " + ((((double) time) / 1000000000)) + " seconds.");
		java.util.Stack<FourInARowMove> javaStack = new java.util.Stack<FourInARowMove>();
		time = System.nanoTime();
		for (int i = 0; i < 10000000; i++) {
			javaStack.push(new FourInARowMove(3));
		}
		time = System.nanoTime() - time;
		System.out.println("Java stack finished in: " + ((((double) time) / 1000000000)) + " seconds.");
	}

}
