package minimax.tictactoe.performance.arrays;

import utils.implementation.core.EvaluationFunction;
import utils.performance.PerformanceEvaluationFunction;
import utils.performance.PrintUtilities;
import utils.performance.TimingUtilities;
import minimax.fourinarow.core.arrays.core.Piece;
import minimax.tictactoe.core.arrays.TicTacToeEvaluationFunction;
import minimax.tictactoe.core.arrays.TicTacToeMove;
import minimax.tictactoe.core.arrays.TicTacToeGameState;
import minimax.tictactoe.core.arrays.TicTacToeMiniMaxAgent;
import minimax.tictactoe.core.arrays.TicTacToeMoveGeneration;

/**
 * Used to test performance of TicTacToeAgent.
 * 
 * @author Riley McCuen
 *
 */
public class TicTacToePerformanceAgent extends TicTacToeMiniMaxAgent {

	private static int EMPTY_MOVE = 0;
	private static Piece[][] EMPTY_BOARD = new Piece[][] { { Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___ },
			{ Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___ },
			{ Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___ } };
	private static TicTacToeGameState EMPTY_STATE = new TicTacToeGameState(EMPTY_MOVE, true, EMPTY_BOARD,
			Piece.PLAYER_ONE);

	public TicTacToePerformanceAgent(TicTacToeGameState gameState, EvaluationFunction<TicTacToeGameState> evaluator) {
		super(gameState, new TicTacToeMoveGeneration(), evaluator);
	}

	public static void main(String[] args) {
		PerformanceEvaluationFunction<TicTacToeGameState> evaluator = new PerformanceEvaluationFunction<TicTacToeGameState>(
				new TicTacToeEvaluationFunction());
		TicTacToePerformanceAgent tester = new TicTacToePerformanceAgent(EMPTY_STATE.deepCopy(true), evaluator);
		TimingUtilities<TicTacToeMove, TicTacToeGameState> timer = new TimingUtilities<TicTacToeMove, TicTacToeGameState>();
		long time = timer.timedEvaluate(tester, 9, true);
		PrintUtilities.printWithWords(9, time, evaluator.getCounterString());
	}

}
