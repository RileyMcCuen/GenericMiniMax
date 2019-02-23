package utils.implementation.minimax.threadsafe;

import utils.implementation.core.AbstractGameState;
import utils.implementation.core.AbstractMove;

/**
 * An implementing Agent will be thread safe. This implementation requires that
 * a fresh externally mutable copy of the game-state is passed to every layer.
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
public interface ThreadSafeMiniMaxAgent<M extends AbstractMove, G extends AbstractGameState<M>> {

	/**
	 * This method is called by iterativeSearch(). It performs a single depth
	 * search. Can throw an InterruptedException which should be caught by
	 * iterativeSearch().
	 * 
	 * @param gameState - the current gameState to perform a search on.
	 * @param depth     - the depth to search until.
	 * @param findMax   - whether to call findMax() first or not.
	 * @return - best move found.
	 * @throws InterruptedException - if the search runs out of time this should be
	 *                              propagated out to the iterative deepening search
	 *                              method that called this search.
	 */
	public M search(G gameState, int depth, boolean findMax) throws InterruptedException;

	/**
	 * Search that returns the best move from the deepest completed depth.
	 * 
	 * @param minDepth  - minimum depth to start iterative deepening from.
	 * @param maxDepth  - the deepest depth that iterative deepening will search to.
	 * @param findMax   - whether to call findMax() first or not.
	 * @param time      - the amount of time to allow the search to run. This does
	 *                  not include the amount of time to start and stop the search.
	 *                  Depending on the complexity of your implementations this can
	 *                  take up to a tenth of a second or more. If this is a concern
	 *                  give a slightly smaller amount of time than allowed to
	 *                  guarantee a finish.
	 * @return - best move found.
	 */
	public M iterativeSearch(G gameState, int minDepth, int maxDepth, boolean findMax, long time);

}
