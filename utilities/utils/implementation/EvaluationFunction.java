package utils.implementation;

/**
 * Represents a call-able evaluation function that an Agent needs to evaluate
 * game-states.
 * 
 * @author Riley McCuen
 *
 * @param <G> The implementation of GameState for the game that this game-state
 *        represents.
 */

public interface EvaluationFunction<G extends AbstractGameState<?>> {

	/**
	 * 
	 * A heuristic evaluation function that can return a value of the gameState.
	 * 
	 * @param gameState - The state of the game that encapsulates all necessary
	 *                  information for move generation.
	 * @return - int value that represents the quality of the gameState.
	 */
	public int evaluate(G gameState, int positiveTerminalEvaluation, int negativeTerminalEvaluation);

}
