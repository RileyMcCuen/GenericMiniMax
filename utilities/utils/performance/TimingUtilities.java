package utils.performance;

import utils.implementation.AbstractGameState;
import utils.implementation.AbstractMove;
import utils.implementation.minimax.AbstractMiniMaxAgent;

/**
 * 
 * Useful timing utility that can be used to time searches in various ways.
 * 
 * @author Riley McCuen
 *
 * @param <M> - the move of the game that is being timed.
 * @param <G> - the game-state of the game that is being timed.
 */

public class TimingUtilities<M extends AbstractMove, G extends AbstractGameState<M>> {

	/**
	 * Times a single search.
	 * 
	 * @param agent
	 * @param depth
	 * @param findMax
	 * @return - how long the search took in nano seconds.
	 */
	public long timedEvaluate(AbstractMiniMaxAgent<M, G> agent, int depth, boolean findMax) {
		long start = System.nanoTime();
		agent.search(depth, findMax);
		return System.nanoTime() - start;
	}

	/**
	 * Times and averages several searches.
	 * 
	 * @param iterations - the number of searches to make.
	 * @param agent
	 * @param depth
	 * @param findMax
	 * @return - the average search time in nano seconds after the specified
	 *         iterations.
	 */
	public long averageTimedEvaluate(int iterations, AbstractMiniMaxAgent<M, G> agent, int depth, boolean findMax) {
		long total = 0;
		for (int i = 0; i < iterations; ++i) {
			total += timedEvaluate(agent, depth, findMax) / iterations;
		}
		return total;
	}

}
