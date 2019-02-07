package utils.implementation.minimax.threadsafepv;

import utils.datastructures.stack.Stack;
import utils.implementation.core.AbstractGameState;
import utils.implementation.core.AbstractMove;

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
public interface MoveGenerationWithPV<M extends AbstractMove, G extends AbstractGameState<M>> {

	/**
	 * 
	 * Generates all possible moves that can be made at the current gameState.
	 * 
	 * @param gameState        - The state of the game that encapsulates all
	 *                         necessary information for move generation.
	 * @param firstMoveAtDepth - the current move of the PV stack.
	 * @return - a stack of moves.
	 */
	public Stack<M> generateMoves(G gameState, M firstMoveAtDetpth);

}
