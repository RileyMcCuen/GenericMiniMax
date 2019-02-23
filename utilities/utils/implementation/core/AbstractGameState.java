package utils.implementation.core;

/**
 * 
 * The is the game-state that represents a game at any point. This object should
 * be used to represent that state of your game for your whole implementation.
 * As the game progress the game-state will be updated and when the Agent should
 * search for its next move a mutable copy of the game-state should be offered.
 * 
 * @author Riley McCuen
 *
 * @param <M> - The implementation of AbstractMove for the game that this
 *        game-state represents.
 */

public abstract class AbstractGameState<M extends AbstractMove> extends Object {

	/**
	 * The turn that the game is currently at.
	 */
	protected int plyNumber;

	public AbstractGameState(int plyNumber) {
		this.plyNumber = plyNumber;
	}

	public int getPlyNumber() {
		return this.plyNumber;
	}

	/**
	 * 
	 * The game-state should take in the move and use all necessary move data to
	 * manipulate itself as if this move was made.
	 * 
	 * @param move - the data representation of a move in for this game.
	 */
	public abstract void makeMove(M move);

	/**
	 * The game-state should take in the move and use all necessary move data to
	 * manipulate itself as if this move was being unmade. If this method is called
	 * with the same move that makeMove() was called with it should completely
	 * revert the game-state to exactly what it was before makeMove() was called.
	 * 
	 * @param move - the data representation of a move in for this game.
	 * 
	 */
	public abstract void undoMove(M move);

	/**
	 * Can be used for move ordering look-up and transposition tables if the Agent
	 * has access to these. GameStates need to be hashed quickly to make these
	 * tables worthwhile, if this is not possible it may be better to use an Agent
	 * that does not use tables.
	 */
	@Override
	public abstract int hashCode();

	/**
	 * Can be used extract a string representation of the move. Can be helpful for
	 * debugging purposes or in a terminal based game. This method is not called by
	 * default and is not required to return a good representation of the move for
	 * an implementation of AbstractMove to perform as expected.
	 */
	@Override
	public abstract String toString();

}
