package utils.implementation.core;

/**
 * 
 * @author Riley McCuen
 *
 * @param <G> - extends AbstractGameState
 */
public interface DeepCopy<G extends AbstractGameState<?>> {

	/**
	 * Performs a full deep copy of all items in the given game-state to a new one.
	 * All values should be copied and the final copy should have no references to
	 * the original object.
	 * 
	 * @param gameState - the game-state to be copied
	 * @return a exact copy of the given game-state
	 */
	public G deepCopy(G gameState);

}
