package utils.implementation.core;

/**
 * Sample wrapper object can be mutated to hold a move
 * when conditions are right.
 * 
 * @author Riley McCuen
 *
 * @param <M> - the type of move to hold.
 */
public class MutableMove<M extends AbstractMove> {

	private M move;

	public M getMove() {
		return move;
	}

	public void setMove(M move) {
		this.move = move;
	}
}
