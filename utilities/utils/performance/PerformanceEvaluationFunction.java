package utils.performance;

import utils.implementation.AbstractGameState;
import utils.implementation.EvaluationFunction;

/**
 * 
 * This class is made to test the performance of any Agent by counting the
 * number of boards that it has calculated in a search.
 * 
 * @author Riley McCuen
 *
 * @param <G> - the game-state implementation for this game being tested.
 */
public class PerformanceEvaluationFunction<G extends AbstractGameState<?>> implements EvaluationFunction<G> {

	private BoardCounter counter;
	private EvaluationFunction<G> evaluator;

	public PerformanceEvaluationFunction(EvaluationFunction<G> evaluator) {
		this.counter = new BoardCounter();
		this.evaluator = evaluator;
	}

	/**
	 * Performs an evaluation and increments number of boards evaluated each time.
	 */
	@Override
	public int evaluate(G gameState, int positiveTerminalEvaluation, int negativeTerminalEvaluation) {
		counter.incrementNumBoardsEvaluated();
		return evaluator.evaluate(gameState, negativeTerminalEvaluation, negativeTerminalEvaluation);
	}

	public long getCount() {
		return this.counter.getNumBoardsEvaluated();
	}

	public void resetCounter() {
		this.counter = new BoardCounter();
	}

	public String getCounterString() {
		return counter.toString();
	}

}
