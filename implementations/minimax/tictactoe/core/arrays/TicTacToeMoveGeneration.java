package minimax.tictactoe.core.arrays;

import java.util.LinkedList;

import minimax.fourinarow.core.arrays.Piece;
import utils.implementation.MoveGeneration;

/**
 * The standard move generation function for Tic Tac Toe.
 * 
 * @author Riley McCuen
 *
 */
public class TicTacToeMoveGeneration implements MoveGeneration<TicTacToeMove, TicTacToeGameState> {

	@Override
	public Iterable<TicTacToeMove> generateMoves(TicTacToeGameState gameState) {
		LinkedList<TicTacToeMove> moveList = new LinkedList<TicTacToeMove>();
		Piece[][] board = gameState.getBoard();
		for (int row = 0; row < TicTacToeGameState.ROWS; ++row) {
			for (int column = 0; column < TicTacToeGameState.COLUMNS; ++column) {
				if (board[row][column] == Piece.__EMPTY___) {
					moveList.add(new TicTacToeMove(row, column));
				}
			}
		}
		return moveList;
	}

}
