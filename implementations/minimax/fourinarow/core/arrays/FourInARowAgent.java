package minimax.fourinarow.core.arrays;

import minimax.fourinarow.core.arrays.core.FourInARowEvaluationFunction;
import minimax.fourinarow.core.arrays.core.FourInARowGameState;
import minimax.fourinarow.core.arrays.core.FourInARowMove;
import minimax.fourinarow.core.arrays.core.FourInARowMoveGeneration;
import utils.implementation.core.EvaluationFunction;
import utils.implementation.core.MoveGeneration;
import utils.implementation.minimax.notthreadsafe.AbstractMiniMaxAgent;

/**
 * This is the Agent that should be instantiated for a 2D array based Four In A
 * Row game and when given a mutable game state this agent can then search the
 * game tree and find the best possible move.
 * 
 * @author Riley McCuen
 *
 */
public class FourInARowAgent extends AbstractMiniMaxAgent<FourInARowMove, FourInARowGameState> {

	public FourInARowAgent(FourInARowGameState gameState,
			MoveGeneration<FourInARowMove, FourInARowGameState> moveGenerator,
			EvaluationFunction<FourInARowGameState> evaluator) {
		super(gameState, moveGenerator, evaluator);
	}

	/**
	 * Initializes a new Four In A Row Agent with the standard 2D array based move
	 * generation and evaluation functions.
	 * 
	 * @param gameState
	 */
	public FourInARowAgent(FourInARowGameState gameState) {
		super(gameState, new FourInARowMoveGeneration(), new FourInARowEvaluationFunction());
	}

	/**
	 * If the depth is trying to go beyond the maximum number of turns in the game
	 * then it is reduced to the maximum depth left in the game.
	 */
	@Override
	public FourInARowMove search(int depth, boolean findMax) {
		if (depth + gameState.getPlyNumber() > FourInARowGameState.MAX_NUMBER_OF_PLYS) {
			depth = FourInARowGameState.MAX_NUMBER_OF_PLYS - gameState.getPlyNumber();
		}
		return super.search(depth, findMax);
	}

	/**
	 * If the depth is trying to go beyond the maximum number of turns in the game
	 * then it is reduced to the maximum depth left in the game.
	 */
	@Override
	public FourInARowMove searchIterativeDeepening(int minDepth, int maxDepth, boolean findMax, long time) {
		if (minDepth + gameState.getPlyNumber() > FourInARowGameState.MAX_NUMBER_OF_PLYS) {
			minDepth = FourInARowGameState.MAX_NUMBER_OF_PLYS - gameState.getPlyNumber();
		}
		if (maxDepth + gameState.getPlyNumber() > FourInARowGameState.MAX_NUMBER_OF_PLYS) {
			maxDepth = FourInARowGameState.MAX_NUMBER_OF_PLYS - gameState.getPlyNumber();
		}
		return super.searchIterativeDeepening(minDepth, maxDepth, findMax, time);
	}

	@Override
	public String toString() {
		return "I am a Four In A Row playing minimax agent";
	}

}
