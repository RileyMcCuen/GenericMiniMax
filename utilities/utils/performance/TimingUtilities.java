package utils.performance;

import utils.implementation.AbstractGameState;
import utils.implementation.AbstractMove;
import utils.implementation.minimax.notthreadsafe.AbstractMiniMaxAgent;

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

	/**
	 * Used to check the time that iterative deepening requires for a single search.
	 * 
	 * @param agent
	 * @param depth
	 * @param findMax
	 * @return - the average search time in nano seconds after the specified
	 *         iterations.
	 */
	public long timedEvaluateIterative(AbstractMiniMaxAgent<M, G> agent, int depth, boolean findMax) {
		long start = System.nanoTime();
		agent.searchIterativeDeepening(depth, depth, findMax, Long.MAX_VALUE);
		return System.nanoTime() - start;
	}

	/**
	 * Times and averages several searches using iterative deepening at one level.
	 * 
	 * @param iterations
	 * @param agent
	 * @param depth
	 * @param findMax
	 * @return - the average search time in nano seconds after the specified
	 *         iterations.
	 */
	public long averageTimedEvaluateIterative(int iterations, AbstractMiniMaxAgent<M, G> agent, int depth,
			boolean findMax) {
		long total = 0;
		for (int i = 0; i < iterations; ++i) {
			total += timedEvaluateIterative(agent, depth, findMax) / iterations;
		}
		return total;
	}

	/**
	 * Times the iterative deepening method going through all its levels.
	 * 
	 * @param agent
	 * @param minDepth
	 * @param maxDepth
	 * @param findMax
	 * @return - the average search time in nano seconds after the specified
	 *         iterations.
	 */
	public long timedEvaluateIterativeMultipleDepths(AbstractMiniMaxAgent<M, G> agent, int minDepth, int maxDepth,
			boolean findMax) {
		long start = System.nanoTime();
		agent.searchIterativeDeepening(minDepth, maxDepth, findMax, Long.MAX_VALUE);
		return System.nanoTime() - start;
	}

	/**
	 * * Times and averages several searches using iterative deepening in the range
	 * applied.
	 * 
	 * @param iterations
	 * @param agent
	 * @param minDepth
	 * @param maxDepth
	 * @param findMax
	 * @return - the average search time in nano seconds after the specified
	 *         iterations.
	 */
	public long averageTimedEvaluationIterativeMultipleDepths(int iterations, AbstractMiniMaxAgent<M, G> agent,
			int minDepth, int maxDepth, boolean findMax) {
		long total = 0;
		for (int i = 0; i < iterations; ++i) {
			total += timedEvaluateIterativeMultipleDepths(agent, minDepth, maxDepth, findMax) / iterations;
		}
		return total;
	}

}
