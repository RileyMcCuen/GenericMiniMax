package minimax.tictactoe.core.arrays;

import utils.implementation.AbstractMove;

/**
 * The standard move implementation for Tic Tac Toe.
 * 
 * @author Riley McCuen
 *
 */
public class TicTacToeMove extends AbstractMove {

	/**
	 * The row and column that represent this move.
	 */
	private final int row, column;

	public TicTacToeMove(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return "Row: " + row + ", Column: " + column;
	}

}
