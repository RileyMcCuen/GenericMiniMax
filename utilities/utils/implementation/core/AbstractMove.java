package utils.implementation.core;

/**
 * 
 * Represents a move for a game that can have as many variables as necessary to
 * represent a single move in a game. This class essentially acts a struct that
 * can pass in data to a game-state that represents a move that the game-state
 * can use to modify itself with. The Object is not meant to be modified after
 * creation and is not meant to modify the game-state.
 * 
 * @author Riley McCuen
 *
 */

public abstract class AbstractMove extends Object {

	/**
	 * The value assigned to this move after its changes to the game-state have been
	 * evaluated.
	 */
	protected int value = 0;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Can be used extract a string representation of the move. Can be helpful for
	 * debugging purposes or in a terminal based game. This method is not called by
	 * default and is not required to return a good representation of the move for
	 * an implementation of AbstractMove to perform as expected.
	 */
	@Override
	public abstract String toString();

}
