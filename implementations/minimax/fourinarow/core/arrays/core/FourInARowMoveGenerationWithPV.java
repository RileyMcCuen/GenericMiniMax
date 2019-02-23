package minimax.fourinarow.core.arrays.core;

import utils.datastructures.stack.Stack;
import utils.implementation.minimax.threadsafepv.MoveGenerationWithPV;

public class FourInARowMoveGenerationWithPV implements MoveGenerationWithPV<FourInARowMove, FourInARowGameState> {

	/**
	 * The column ordering that always picks the closest to center column that is
	 * not full first. In traditional Four In A Row columns closer to the center
	 * often have a better value associated with them.
	 */
	private static int[] CENTER_FOCUSED_COLUMN_ORDERING = new int[] { 3, 2, 4, 1, 5, 0, 6 };

	@Override
	public Stack<FourInARowMove> generateMoves(FourInARowGameState gameState, FourInARowMove firstMoveAtDepth) {
		Stack<FourInARowMove> moveList = new Stack<FourInARowMove>();
		if (firstMoveAtDepth != null) {
			int firstMove = firstMoveAtDepth.getColumn();
			for (int column : CENTER_FOCUSED_COLUMN_ORDERING) {
				if (gameState.getNextOpenRowInColumn(column) >= 0 && column != firstMove) {
					moveList.pushMove(new FourInARowMove(column));
				}
			}
			moveList.pushMove(firstMoveAtDepth);
		} else {
			for (int column : CENTER_FOCUSED_COLUMN_ORDERING) {
				if (gameState.getNextOpenRowInColumn(column) >= 0) {
					moveList.pushMove(new FourInARowMove(column));
				}
			}
		}
		return moveList;
	}

}
