package minimax.fourinarow.core.arrays.core;

import java.util.Arrays;

import utils.implementation.core.AbstractGameState;

/**
 * 
 * 2D array based Four In A Row specific game-state.
 * 
 * @author Riley McCuen
 *
 */

public class FourInARowGameState extends AbstractGameState<FourInARowMove> {

	public static int ROWS = 6;
	public static int COLUMNS = 7;
	public static int MAX_NUMBER_OF_PLYS = 42;

	/**
	 * 2D array "Game Board" filled with pieces. Has dimensions ROWS X COLUMNS.
	 */
	private Piece[][] board;
	private Piece currentPlayer;

	/**
	 * The next open row in every column. The bottom row is 6 and the top row is 0.
	 */
	private int[] nextOpenRowInColumns;

	public FourInARowGameState(int plyNumber, Piece[][] board, Piece currentPlayer) {
		super(plyNumber);
		this.board = board;
		this.currentPlayer = currentPlayer;
		this.nextOpenRowInColumns = new int[COLUMNS];
		this.calculateNextOpenRowInColumns();
	}

	/**
	 * 
	 * @param plyNumber
	 * @param board
	 * @param currentPlayer
	 * @param nextOpenRowInColumns
	 */
	public FourInARowGameState(int plyNumber, Piece[][] board, Piece currentPlayer, int[] nextOpenRowInColumns) {
		super(plyNumber);
		this.board = copyBoard(board);
		this.currentPlayer = currentPlayer;
		this.nextOpenRowInColumns = Arrays.copyOf(nextOpenRowInColumns, COLUMNS);
	}

	/**
	 * 
	 * Creates a new copy of the given board. Utilized in the private constructor.
	 * 
	 * @param board
	 * @return
	 */
	private Piece[][] copyBoard(Piece[][] board) {
		Piece[][] copy = new Piece[ROWS][];
		for (int row = 0; row < ROWS; ++row) {
			copy[row] = Arrays.copyOf(board[row], COLUMNS);
		}
		return copy;
	}

	/**
	 * If this is externally mutable then a reference to this game-state's board is
	 * returned directly otherwise a copy is made and returned.
	 * 
	 * @return - either reference or copy of current game board.
	 */
	public Piece[][] getBoard() {
		return copyBoard(board);
	}

	public Piece getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * If this is externally mutable then a reference is returned directly otherwise
	 * a copy is made and returned.
	 * 
	 * @return - either reference or copy of the current array of next move in every
	 *         column.
	 */
	public int[] getNextOpenRowInColumns() {
		return Arrays.copyOf(nextOpenRowInColumns, COLUMNS);
	}

	/**
	 * 
	 * @param column - the column to look for the next open move in.
	 * @return - the next open row in the column.
	 */
	public int getNextOpenRowInColumn(int column) {
		return nextOpenRowInColumns[column];
	}

	/**
	 * Places a piece on the board and decrements the next open space in that
	 * column. Increments plyNumber then swaps current players.
	 */
	@Override
	public void makeMove(FourInARowMove move) {
		board[nextOpenRowInColumns[move.getColumn()]--][move.getColumn()] = currentPlayer;
		plyNumber++;
		currentPlayer = Piece.swapPlayerPiece(currentPlayer);
	}

	/**
	 * Deletes a piece off of the board and increments the next open space in that
	 * column. Decrements plyNumber then swaps current players.
	 */
	@Override
	public void undoMove(FourInARowMove move) {
		board[++nextOpenRowInColumns[move.getColumn()]][move.getColumn()] = Piece.__EMPTY___;
		plyNumber--;
		currentPlayer = Piece.swapPlayerPiece(currentPlayer);
	}

	/**
	 * Calculates the lowest empty row in every column.
	 */
	private void calculateNextOpenRowInColumns() {
		for (int column = 0; column < COLUMNS; ++column) {
			for (int row = ROWS - 1; row >= 0; --row) {
				if (board[row][column] == Piece.__EMPTY___) {
					nextOpenRowInColumns[column] = row;
					break;
				}
			}
		}
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(board);
	}

	@Override
	public String toString() {
		StringBuilder stateString = new StringBuilder();
		stateString.append("Current Player : " + currentPlayer.name() + "\n");
		stateString.append("Ply: " + plyNumber + "\n");
		for (Piece[] row : board) {
			for (Piece piece : row) {
				stateString.append(piece.name() + " ");
			}
			stateString.append("\n");
		}
		return stateString.toString();
	}

}
