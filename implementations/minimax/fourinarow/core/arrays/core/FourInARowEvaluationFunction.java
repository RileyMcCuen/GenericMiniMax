package minimax.fourinarow.core.arrays.core;

import utils.implementation.core.EvaluationFunction;

/**
 * 
 * The standard evaluation function for 2D array based Four In A Row.
 * 
 * @author Riley McCuen
 *
 */

public class FourInARowEvaluationFunction implements EvaluationFunction<FourInARowGameState> {

	/**
	 * Maximum offset when checking contiguous four piece chunks.
	 */
	private static int MAXIMUM_OFFSET = 4;

	/**
	 * Terminal values.
	 */
	private static int FOUR_IN_A_ROW_POS_VALUE = 4;
	private static int FOUR_IN_A_ROW_NEG_VALUE = -4;
	private static int TERMINAL_MULTIPLIER = 25;

	/**
	 * Future threat values.
	 */
	private static int THREE_IN_A_ROW_POS_VALUE = 3;
	private static int THREE_IN_A_ROW_NEG_VALUE = -3;

	/**
	 * Default 0 value.
	 */
	private static int NO_VALUE = 0;

	/**
	 * Heuristic conditional numbers. Some heuristics can't accept high or low
	 * column values or high row values.
	 */
	private static int MAX_ROW = 3;
	private static int MIN_COLUMN = 2;
	private static int MAX_COLUMN = 4;

	/**
	 * Checks the value of a given board in the vertical direction.
	 * 
	 * @param row    - the starting row of the four piece check.
	 * @param column - the starting column of the four piece check.
	 * 
	 * @return The value of the check with the given initial row and column.
	 */
	private static int checkVerticalHeuristic(int row, int column, Piece[][] board) {
		int value = NO_VALUE;
		for (int offset = 0; offset < MAXIMUM_OFFSET; ++offset) {
			value = value + board[row + offset][column].getValue();
		}
		if (value == FOUR_IN_A_ROW_POS_VALUE || value == FOUR_IN_A_ROW_NEG_VALUE) {
			return (value * TERMINAL_MULTIPLIER);
		}
		if (value == THREE_IN_A_ROW_POS_VALUE || value == THREE_IN_A_ROW_NEG_VALUE) {
			return Integer.signum(value);
		}
		return NO_VALUE;
	}

	/**
	 * Checks the value of a given board in the horizontal direction.
	 * 
	 * @param row    - the starting row of the four piece check.
	 * @param column - the starting column of the four piece check.
	 * 
	 * @return The value of the check with the given initial row and column.
	 */
	private static int checkHorizontalHeuristic(int row, int column, Piece[][] board) {
		int value = NO_VALUE;
		for (int offset = 0; offset < MAXIMUM_OFFSET; ++offset) {
			value = value + board[row][column + offset].getValue();
		}
		if (value == FOUR_IN_A_ROW_POS_VALUE || value == FOUR_IN_A_ROW_NEG_VALUE) {
			return (value * TERMINAL_MULTIPLIER);
		}
		if (value == THREE_IN_A_ROW_POS_VALUE || value == THREE_IN_A_ROW_NEG_VALUE) {
			return Integer.signum(value);
		}
		return NO_VALUE;
	}

	/**
	 * Checks the value of a given board in the diagonal up direction.
	 * 
	 * @param row    - the starting row of the four piece check.
	 * @param column - the starting column of the four piece check.
	 * 
	 * @return The value of the check with the given initial row and column.
	 */
	private static int checkDiagonalUpHeuristic(int row, int column, Piece[][] board) {
		int value = NO_VALUE;
		for (int offset = 0; offset < MAXIMUM_OFFSET; ++offset) {
			value = value + board[row + offset][column - offset].getValue();
		}
		if (value == FOUR_IN_A_ROW_POS_VALUE || value == FOUR_IN_A_ROW_NEG_VALUE) {
			return (value * TERMINAL_MULTIPLIER);
		}
		if (value == THREE_IN_A_ROW_POS_VALUE || value == THREE_IN_A_ROW_NEG_VALUE) {
			return Integer.signum(value);
		}
		return NO_VALUE;
	}

	/**
	 * Checks the value of a given board in the diagonal down direction.
	 * 
	 * @param row    - the starting row of the four piece check.
	 * @param column - the starting column of the four piece check.
	 * 
	 * @return The value of the check with the given initial row and column.
	 */
	private static int checkDiagonalDownHeuristic(int row, int column, Piece[][] board) {
		int value = NO_VALUE;
		for (int offset = 0; offset < MAXIMUM_OFFSET; ++offset) {
			value = value + board[row + offset][column + offset].getValue();
		}
		if (value == FOUR_IN_A_ROW_POS_VALUE || value == FOUR_IN_A_ROW_NEG_VALUE) {
			return (value * TERMINAL_MULTIPLIER);
		}
		if (value == THREE_IN_A_ROW_POS_VALUE || value == THREE_IN_A_ROW_NEG_VALUE) {
			return Integer.signum(value);
		}
		return NO_VALUE;
	}

	/**
	 * Evaluates the game-state by looking at ever legal contiguous way to get four
	 * pieces in a row. If there is a four in a row of all the same pieces then the
	 * game is over and the corresponding value should be returned. At the same time
	 * the heuristic is also calculating the number of contiguous four in a rows
	 * that have three of one color and one empty space which indicates a good
	 * future move for one of the players. If there is a win for a player that value
	 * alone is returned otherwise a composite of the number of open three in a row
	 * spots is returned as the value of this game-state.
	 */
	@Override
	public int evaluate(FourInARowGameState gameState, int positiveTerminalEvaluation, int negativeTerminalEvaluation) {
		int evaluation = NO_VALUE;
		for (int row = 0; row < FourInARowGameState.ROWS; ++row) {
			for (int column = 0; column < FourInARowGameState.COLUMNS; ++column) {
				if (row < MAX_ROW) {
					int vert = checkVerticalHeuristic(row, column, gameState.getBoard());
					if (vert == positiveTerminalEvaluation || vert == negativeTerminalEvaluation) {
						return vert;
					}
					evaluation = evaluation + vert;
				}
				if (column < MAX_COLUMN) {
					int horiz = checkHorizontalHeuristic(row, column, gameState.getBoard());
					if (horiz == positiveTerminalEvaluation || horiz == negativeTerminalEvaluation) {
						return horiz;
					}
					evaluation = evaluation + horiz;
				}
				if (column > MIN_COLUMN && row < MAX_ROW) {
					int up = checkDiagonalUpHeuristic(row, column, gameState.getBoard());
					if (up == positiveTerminalEvaluation || up == negativeTerminalEvaluation) {
						return up;
					}
					evaluation = evaluation + up;
				}
				if (column < MAX_COLUMN && row < MAX_ROW) {
					int down = checkDiagonalDownHeuristic(row, column, gameState.getBoard());
					if (down == positiveTerminalEvaluation || down == negativeTerminalEvaluation) {
						return down;
					}
					evaluation = evaluation + down;
				}
			}
		}
		return evaluation;
	}

}
