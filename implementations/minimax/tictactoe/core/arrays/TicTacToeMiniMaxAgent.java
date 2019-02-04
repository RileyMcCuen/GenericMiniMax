package minimax.tictactoe.core.arrays;

import utils.implementation.EvaluationFunction;
import utils.implementation.MoveGeneration;
import utils.implementation.minimax.AbstractMiniMaxAgent;

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
	public String toString() {
		return "I am a perfect Tic Tac Toe Agent.";
	}

}
