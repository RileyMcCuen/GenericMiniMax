package minimax.tictactoe.core.arrays;

import java.util.Arrays;

import minimax.fourinarow.core.arrays.core.Piece;
import utils.implementation.core.AbstractGameState;

/**
 * Tic Tac Toe game-state. Reuses the Piece enumeration from Four In A Row.
 * 
 * @author Riley McCuen
 *
 */
public class TicTacToeGameState extends AbstractGameState<TicTacToeMove> {

	public final static int MAXIMUM_NUMBER_OF_PLYS = 9;
	public final static int ROWS = 3;
	public final static int COLUMNS = 3;

	private Piece[][] board;
	private Piece currentPlayer;

	public TicTacToeGameState(int plyNumber, Piece[][] board, Piece currentPlayer) {
		super(plyNumber);
		this.board = board;
		this.currentPlayer = currentPlayer;
	}

	@Override
	public void makeMove(TicTacToeMove move) {
		board[move.getRow()][move.getColumn()] = currentPlayer;
		plyNumber++;
		currentPlayer = Piece.swapPlayerPiece(currentPlayer);
	}

	@Override
	public void undoMove(TicTacToeMove move) {
		board[move.getRow()][move.getColumn()] = Piece.__EMPTY___;
		plyNumber--;
		currentPlayer = Piece.swapPlayerPiece(currentPlayer);
	}

	/**
	 * 
	 * @return - copy of the current board.
	 */
	private Piece[][] copyBoard() {
		Piece[][] copy = new Piece[ROWS][];
		for (int row = 0; row < ROWS; ++row) {
			copy[row] = Arrays.copyOf(board[row], COLUMNS);
		}
		return copy;
	}

	/**
	 * Gets the current game board.
	 * 
	 * @return If this is externallyMutable returns a reference to this game-state's
	 *         game board otherwise returns a copy.
	 */
	public Piece[][] getBoard() {
		return copyBoard();
	}

	public Piece getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(board);
	}

	@Override
	public String toString() {
		StringBuilder stateString = new StringBuilder();
		stateString.append("Ply: " + plyNumber + "\n");
		stateString.append("Current Player: " + currentPlayer.name() + "\n");
		for (Piece[] row : board) {
			for (Piece piece : row) {
				stateString.append(piece.name() + " ");
			}
			stateString.append("\n");
		}
		return stateString.toString();
	}

}
