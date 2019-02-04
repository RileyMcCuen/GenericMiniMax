package minimax.fourinarow.performance.arrays;

import minimax.fourinarow.core.arrays.FourInARowEvaluationFunction;
import minimax.fourinarow.core.arrays.FourInARowGameState;
import utils.performance.BoardCounter;

/**
 * 
 * This evaluation function is that standard evaluation function but it also
 * automatically increments the number of searched boards every time that it is
 * called.
 * 
 * @author Riley McCuen
 *
 */
public class FourInARowPerformanceEvaluationFunction extends FourInARowEvaluationFunction {

	private BoardCounter counter = new BoardCounter();

	@Override
	public int evaluate(FourInARowGameState gameState, int positiveTerminalEvaluation, int negativeTerminalEvaluation) {
		counter.incrementNumBoardsEvaluated();
		return super.evaluate(gameState, positiveTerminalEvaluation, negativeTerminalEvaluation);
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
