package minimax.fourinarow.core.arrays.core;

/**
 * 
 * An enumeration of the different values that a board position can take.
 * 
 * @author Riley McCuen
 *
 */
public enum Piece {

	PLAYER_ONE(1), __EMPTY___(0), PLAYER_TWO(-1);

	private final int value;

	private Piece(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	/**
	 * 
	 * @param piece - must either be PLAYER_ONE or PLAYER_TWO.
	 * @return - the other opposite of the one taken in as piece.
	 */
	public static Piece swapPlayerPiece(Piece piece) {
		switch (piece) {
		case PLAYER_ONE:
			return PLAYER_TWO;
		case PLAYER_TWO:
			return PLAYER_ONE;
		default:
			throw new IllegalArgumentException("EMPTY is not a valid Piece to swap.");
		}
	}

}
