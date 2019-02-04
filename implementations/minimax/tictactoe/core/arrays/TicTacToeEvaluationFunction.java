package minimax.tictactoe.core.arrays;

import minimax.fourinarow.core.arrays.Piece;
import utils.implementation.EvaluationFunction;

/**
 * Perfect evaluation function for Tic Tac Toe.
 * 
 * @author Riley McCuen
 *
 */

public class TicTacToeEvaluationFunction implements EvaluationFunction<TicTacToeGameState> {

	/**
	 * All triples of pairs that represent contiguous positions that a win could
	 * result from.
	 */
	private static final int[][][] THREE_IN_ROW_INDICES = new int[][][] { { { 0, 0 }, { 0, 1 }, { 0, 2 } },
			{ { 1, 0 }, { 1, 1 }, { 1, 2 } }, { { 2, 0 }, { 2, 1 }, { 2, 2 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 } },
			{ { 1, 0 }, { 1, 1 }, { 1, 2 } }, { { 2, 0 }, { 2, 1 }, { 2, 2 } }, { { 0, 0 }, { 1, 1 }, { 2, 2 } },
			{ { 2, 0 }, { 1, 1 }, { 0, 2 } } };

	/**
	 * Indexing for the above triples
	 */
	private static final int ROW = 0;
	private static final int COLUMN = 1;
	private static final int FIRST = 0;
	private static final int SECOND = 1;
	private static final int THIRD = 2;

	/**
	 * Perfect evaluation function that only assigns a value if the state is
	 * terminal.
	 */
	@Override
	public int evaluate(TicTacToeGameState gameState, int positiveTerminalEvaluation, int negativeTerminalEvaluation) {
		Piece[][] board = gameState.getBoard();
		for (int[][] threeInRow : THREE_IN_ROW_INDICES) {
			if (board[threeInRow[FIRST][ROW]][threeInRow[FIRST][COLUMN]] == board[threeInRow[SECOND][ROW]][threeInRow[SECOND][COLUMN]]
					&& board[threeInRow[SECOND][ROW]][threeInRow[SECOND][COLUMN]] == board[threeInRow[THIRD][ROW]][threeInRow[THIRD][COLUMN]]) {
				return board[threeInRow[FIRST][ROW]][threeInRow[FIRST][COLUMN]].getValue();
			}
		}
		return 0;
	}

}
