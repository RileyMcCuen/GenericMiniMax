package utils.implementation.minimax.threadsafepv;

import utils.datastructures.stack.Stack;
import utils.implementation.AbstractGameState;
import utils.implementation.AbstractMove;

/**
 * An implementing Agent will be thread safe. This implementation requires that
 * a fresh externally mutable copy of the game-state is passed to every layer.
 * 
 * The move object that is passed in is to put the best move from that depth
 * into and when the function returns the move is accessible in the depth below
 * it.
 * 
 * The methods that return a collection return the best path of moves that was
 * found as the recursion unwound. This collection can be used in the next
 * search as a best first move ordering strategy and the given path will be
 * searched first.
 * 
 * All methods are implemented using iterative deepening. If a single depth
 * search is wanted pass in the same min- and max- depth values into the method
 * that is preferred.
 * 
 * @author Riley McCuen
 *
 * @param <M> - abstract move implementation
 * @param <G> - game-state implementation
 */
public interface ThreadSafePVMiniMaxAgent<M extends AbstractMove, G extends AbstractGameState<M>> {

	/**
	 * This method is called by the other two methods that do iterative deepening.
	 * It performs a single depth search. Can throw an InterruptedException.
	 * 
	 * @param depth   - the depth to search until.
	 * @param findMax - whether to call findMax() first or not.
	 * @return - best move path found.
	 * @throws InterruptedException - if the seach runs out of time this should be
	 *                              propagated out to the iterative deepening search
	 *                              method that called this search.
	 */
	public Stack<M> searchWithPV(int depth, boolean findMax, Stack<M> startPath) throws InterruptedException;

	/**
	 * Search that returns a collection of moves from the best path that has been
	 * found. It uses an initial collection as the beginning path for its search.
	 * 
	 * @param minDepth - minimum depth to start iterative deepening from.
	 * @param maxDepth - the deepest depth that iterative deepening will search to.
	 * @param findMax  - whether to call findMax() first or not.
	 * @param path     - the last best path found
	 * @return - best move path found.
	 */
	public Stack<M> iterativeSearchWithPV(int minDepth, int maxDepth, boolean findMax, Stack<M> startPath);

}
