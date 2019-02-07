package utils.implementation.minimax.threadsafepv;

import utils.datastructures.stack.Stack;
import utils.implementation.core.AbstractGameState;
import utils.implementation.core.AbstractMove;

public class RunnableSearch<M extends AbstractMove, G extends AbstractGameState<M>> implements Runnable {

	private Stack<M> startPath, bestPath;
	private int minDepth, maxDepth;
	private boolean findMax;
	private AbstractThreadSafePVMiniMaxAgent<M, G> runner;

	public RunnableSearch(Stack<M> startPath, int minDepth, int maxDepth, boolean findMax,
			AbstractThreadSafePVMiniMaxAgent<M, G> runner) {
		this.startPath = startPath;
		this.bestPath = null;
		this.minDepth = minDepth;
		this.maxDepth = maxDepth;
		this.findMax = findMax;
		this.runner = runner;
	}

	public Stack<M> getBestPath() {
		return bestPath;
	}

	@Override
	public void run() {
		for (int depth = minDepth; depth <= maxDepth; ++depth) {
			try {
				System.out.println("Depth: " + depth);
				bestPath = runner.searchWithPV(depth, findMax, startPath);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
