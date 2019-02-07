package minimax.fourinarow.core.arrays;

import minimax.fourinarow.core.arrays.core.FourInARowEvaluationFunction;
import minimax.fourinarow.core.arrays.core.FourInARowGameState;
import minimax.fourinarow.core.arrays.core.FourInARowMove;
import utils.datastructures.stack.Stack;
import utils.implementation.core.EvaluationFunction;
import utils.implementation.minimax.threadsafepv.AbstractThreadSafePVMiniMaxAgent;
import utils.implementation.minimax.threadsafepv.MoveGenerationWithPV;

public class FourInARowThreadSafePVAgent extends AbstractThreadSafePVMiniMaxAgent<FourInARowMove, FourInARowGameState> {

	public FourInARowThreadSafePVAgent(FourInARowGameState gameState,
			MoveGenerationWithPV<FourInARowMove, FourInARowGameState> moveGenerator,
			EvaluationFunction<FourInARowGameState> evaluator, int positiveTerminalEvaluation,
			int negativeTerminalEvaluation) {
		super(gameState, moveGenerator, evaluator, positiveTerminalEvaluation, negativeTerminalEvaluation);
	}

	public FourInARowThreadSafePVAgent(FourInARowGameState gameState,
			MoveGenerationWithPV<FourInARowMove, FourInARowGameState> moveGenerator,
			EvaluationFunction<FourInARowGameState> evaluator) {
		super(gameState, moveGenerator, evaluator);
	}

	public FourInARowThreadSafePVAgent(FourInARowGameState gameState) {
		super(gameState, new FourInARowMoveGenerationWithPV(), new FourInARowEvaluationFunction());
	}

	@Override
	public FourInARowMove iterativeSearchWithPV(int minDepth, int maxDepth, boolean findMax,
			Stack<FourInARowMove> startPath, long time) {
		if (minDepth + gameState.getPlyNumber() > FourInARowGameState.MAX_NUMBER_OF_PLYS) {
			minDepth = FourInARowGameState.MAX_NUMBER_OF_PLYS - gameState.getPlyNumber();
		}
		if (maxDepth + gameState.getPlyNumber() > FourInARowGameState.MAX_NUMBER_OF_PLYS) {
			maxDepth = FourInARowGameState.MAX_NUMBER_OF_PLYS - gameState.getPlyNumber();
		}
		return super.iterativeSearchWithPV(minDepth, maxDepth, findMax, startPath, time);
	}

	@Override
	public String toString() {
		return "I am a Four In A Row playing minimax agent";
	}

}
