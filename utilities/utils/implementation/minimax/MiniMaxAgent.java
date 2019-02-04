package utils.implementation.minimax;

import utils.implementation.AbstractMove;

/**
 * 
 * This is the interface that any simple MiniMaxAgent must implement. The
 * methods should call the appropriate functions to perform a Min-Max search.
 * The AbstractMiniMaxAgent proves an default implementation of the algorithm.
 * 
 * @author Riley McCuen
 *
 */

public interface MiniMaxAgent<M extends AbstractMove> {

	/**
	 * 
	 * Performs a Min-Max search on the game tree with the given maximum-depth.
	 * 
	 * @param depth   - maximum depth that the game-tree should be searched.
	 * @param findMax - whether to call findMax() or findMin() first.
	 */
	public M search(int depth, boolean findMax);

	/**
	 * 
	 * Performs a iteratively deepening Min-Max search on the game tree with the
	 * given maximum-depth. The tree is first searched to the minDepth then searched
	 * again at minDepth + 1 and so on until the final search happens at maxDepth.
	 * 
	 * @param minDepth - the minimum depth to start searching
	 * @param maxDepth - the maximum depth to search till on the last iteration.
	 * @param findMax  - whether to call findMax() or findMin() first.
	 */
	public M searchIterativeDeepening(int minDepth, int maxDepth, boolean findMax, long time);

}
