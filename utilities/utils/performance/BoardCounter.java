package utils.performance;

/**
 * 
 * This class is used to perform a performance evaluation by counting the number
 * of boards that have been evaluated in this search.
 * 
 * @author Riley McCuen
 *
 */

public class BoardCounter extends Object {

	/**
	 * The number of boards that have been evaluated in this search.
	 */
	private long numBoardsEvaluated = 0;

	public BoardCounter() {
	}

	/**
	 * Call this after a complete search to get the total number of evaluated
	 * boards.
	 * 
	 * @return - the number of boards evaluated.
	 */
	public long getNumBoardsEvaluated() {
		return numBoardsEvaluated;
	}

	/**
	 * Call the method every time the evaluation method is called the increment
	 * number of boards evaluated by 1.
	 */
	public void incrementNumBoardsEvaluated() {
		numBoardsEvaluated += 1;
	}

	/**
	 * Resets the counter to get ready for another search.
	 */
	public void resetCounter() {
		numBoardsEvaluated = 0;
	}

	/**
	 * Provides a nice string with the number of boards evaluated.
	 */
	public String toString() {
		return "Number of Boards Evaluated: " + numBoardsEvaluated;
	}

}
