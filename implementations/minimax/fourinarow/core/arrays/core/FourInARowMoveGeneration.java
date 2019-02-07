package minimax.fourinarow.core.arrays.core;

import java.util.LinkedList;

import utils.implementation.core.MoveGeneration;

/**
 * 
 * 2d array based Four In A Row move generation.
 * 
 * @author Riley McCuen
 *
 */

public class FourInARowMoveGeneration implements MoveGeneration<FourInARowMove, FourInARowGameState> {

	/**
	 * The column ordering that always picks the closest to center column that is
	 * not full first. In traditional Four In A Row columns closer to the center
	 * often have a better value associated with them.
	 */
	private static int[] CENTER_FOCUSED_COLUMN_ORDERING = new int[] { 3, 2, 4, 1, 5, 0, 6 };

	/**
	 * Returns a LinkedList of moves utilizing center focused column ordering.
	 */
	@Override
	public Iterable<FourInARowMove> generateMoves(FourInARowGameState gameState) {
		LinkedList<FourInARowMove> moveList = new LinkedList<FourInARowMove>();
		for (int column : CENTER_FOCUSED_COLUMN_ORDERING) {
			if (gameState.getNextOpenRowInColumn(column) >= 0) {
				moveList.add(new FourInARowMove(column));
			}
		}
		return moveList;
	}

}
