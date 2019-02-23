package minimax.fourinarow.core.arrays.agents;

import minimax.fourinarow.core.arrays.core.FourInARowDeepCopier;
import minimax.fourinarow.core.arrays.core.FourInARowEvaluationFunction;
import minimax.fourinarow.core.arrays.core.FourInARowGameState;
import minimax.fourinarow.core.arrays.core.FourInARowMove;
import minimax.fourinarow.core.arrays.core.FourInARowMoveGeneration;
import utils.implementation.core.EvaluationFunction;
import utils.implementation.core.MoveGeneration;
import utils.implementation.minimax.threadsafe.AbstractThreadSafeMiniMaxAgent;

public class FourInARowThreadSafeAgent extends AbstractThreadSafeMiniMaxAgent<FourInARowMove, FourInARowGameState>{

	public FourInARowThreadSafeAgent(FourInARowDeepCopier copier,
			MoveGeneration<FourInARowMove, FourInARowGameState> moveGenerator,
			EvaluationFunction<FourInARowGameState> evaluator) {
		super(copier, moveGenerator, evaluator);
	}

	/**
	 * Initializes a new Four In A Row Agent with the standard 2D array based move
	 * generation and evaluation functions.
	 * 
	 * @param gameState
	 */
	public FourInARowThreadSafeAgent(FourInARowGameState gameState) {
		super(new FourInARowDeepCopier(), new FourInARowMoveGeneration(), new FourInARowEvaluationFunction());
	}


	/**
	 * If the depth is trying to go beyond the maximum number of turns in the game
	 * then it is reduced to the maximum depth left in the game.
	 */
	@Override
	public FourInARowMove iterativeSearch(FourInARowGameState gameState, int minDepth, int maxDepth, boolean findMax, long time) {
		if (minDepth + gameState.getPlyNumber() > FourInARowGameState.MAX_NUMBER_OF_PLYS) {
			minDepth = FourInARowGameState.MAX_NUMBER_OF_PLYS - gameState.getPlyNumber();
		}
		if (maxDepth + gameState.getPlyNumber() > FourInARowGameState.MAX_NUMBER_OF_PLYS) {
			maxDepth = FourInARowGameState.MAX_NUMBER_OF_PLYS - gameState.getPlyNumber();
		}
		return super.iterativeSearch(gameState, minDepth, maxDepth, findMax, time);
	}

	@Override
	public String toString() {
		return "I am a Four In A Row playing minimax agent";
	}
}
