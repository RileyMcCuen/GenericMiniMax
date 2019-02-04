package utils.implementation;

/**
 * Represents a call-able move-generation function that an Agent needs to create
 * legal moves to mutate a game-state.
 * 
 * @author Riley McCuen
 *
 * @param <M> The implementation of AbstractMove for the game that this
 *        game-state represents.
 * @param <G> The implementation of GameState for the game that this game-state
 *        represents.
 */

public interface MoveGeneration<M extends AbstractMove, G extends AbstractGameState<M>> {

	/**
	 * 
	 * Generates all possible moves that can be made at the current gameState.
	 * 
	 * @param gameState - The state of the game that encapsulates all necessary
	 *                  information for move generation.
	 * @return - collection of moves that can be iterated over and used one by one
	 *         to modify the game state.
	 */
	public Iterable<M> generateMoves(G gameState);

}
