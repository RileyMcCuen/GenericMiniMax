package minimax.tictactoe.core.arrays;

import utils.implementation.EvaluationFunction;
import utils.implementation.MoveGeneration;
import utils.implementation.minimax.AbstractMiniMaxAgent;

public class TicTacToeMiniMaxAgent extends AbstractMiniMaxAgent<TicTacToeMove, TicTacToeGameState> {

	public TicTacToeMiniMaxAgent(TicTacToeGameState gameState,
			MoveGeneration<TicTacToeMove, TicTacToeGameState> moveGenerator,
			EvaluationFunction<TicTacToeGameState> evaluator) {
		super(gameState, moveGenerator, evaluator);
		// TODO Auto-generated constructor stub
	}

	public TicTacToeMiniMaxAgent(TicTacToeGameState gameState) {
		super(gameState, new TicTacToeMoveGeneration(), new TicTacToeEvaluationFunction());
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
