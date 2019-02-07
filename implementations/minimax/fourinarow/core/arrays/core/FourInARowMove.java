package minimax.fourinarow.core.arrays.core;

import utils.implementation.core.AbstractMove;

/**
 * 
 * 2D array based Four In A Row move that only needs to indicate which column to
 * drop the piece.
 * 
 * @author Riley McCuen
 *
 */

public class FourInARowMove extends AbstractMove {

	/**
	 * The column that the move will take place in.
	 */
	private final int column;

	public FourInARowMove(int column) {
		this.column = column;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return "Column " + column + ", Value: " + value;
	}

}
