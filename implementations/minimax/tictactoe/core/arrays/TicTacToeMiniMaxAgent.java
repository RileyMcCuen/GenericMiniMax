package minimax.tictactoe.core.arrays;

import utils.implementation.EvaluationFunction;
import utils.implementation.MoveGeneration;
import utils.implementation.minimax.notthreadsafe.AbstractMiniMaxAgent;

public class TicTacToeMiniMaxAgent extends AbstractMiniMaxAgent<TicTacToeMove, TicTacToeGameState> {

	public TicTacToeMiniMaxAgent(TicTacToeGameState gameState,
			MoveGeneration<TicTacToeMove, TicTacToeGameState> moveGenerator,
			EvaluationFunction<TicTacToeGameState> evaluator) {
		super(gameState, moveGenerator, evaluator);
	}

	/**
	 * Initializes the Agent with its standard implementations of its evaluation and
	 * move generation functions.
	 * 
	 * @param gameState - the game-state that needs to be evaluated.
	 */
	public TicTacToeMiniMaxAgent(TicTacToeGameState gameState) {
		super(gameState, new TicTacToeMoveGeneration(), new TicTacToeEvaluationFunction());
	}

	@Override
	public TicTacToeMove search(int depth, boolean findMax) {
		if (depth + gameState.getPlyNumber() > TicTacToeGameState.MAXIMUM_NUMBER_OF_PLYS) {
			depth = TicTacToeGameState.MAXIMUM_NUMBER_OF_PLYS - gameState.getPlyNumber();
		}
		return super.search(depth, findMax);
	}

	@Override
	public TicTacToeMove searchIterativeDeepening(int minDepth, int maxDepth, boolean findMax, long time) {
		if (minDepth + gameState.getPlyNumber() > TicTacToeGameState.MAXIMUM_NUMBER_OF_PLYS) {
			minDepth = TicTacToeGameState.MAXIMUM_NUMBER_OF_PLYS - gameState.getPlyNumber();
		}
		if (maxDepth + gameState.getPlyNumber() > TicTacToeGameState.MAXIMUM_NUMBER_OF_PLYS) {
			maxDepth = TicTacToeGameState.MAXIMUM_NUMBER_OF_PLYS - gameState.getPlyNumber();
		}
		return super.searchIterativeDeepening(minDepth, maxDepth, findMax, time);
	}

	@Override
	public String toString() {
		return "I am a perfect Tic Tac Toe Agent.";
	}

}
