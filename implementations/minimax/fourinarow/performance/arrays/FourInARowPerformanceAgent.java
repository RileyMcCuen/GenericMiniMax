package minimax.fourinarow.performance.arrays;

import minimax.fourinarow.core.arrays.FourInARowAgent;
import minimax.fourinarow.core.arrays.FourInARowEvaluationFunction;
import minimax.fourinarow.core.arrays.FourInARowGameState;
import minimax.fourinarow.core.arrays.FourInARowMoveGeneration;
import minimax.fourinarow.core.arrays.Piece;

/**
 * This Agent is used to count the number of boards that are evaluated at
 * various depths and can be useful to compare evaluation functions. Also can be
 * used to time searches by running the main method.
 * 
 * NOTE: Would not recommend attempting depths more than 15 with this algorithm.
 * It will likely take several hours or for extreme depths an impossible amount
 * of time to ever finish.
 * 
 * @author Riley McCuen
 *
 */
@SuppressWarnings("unused")
public class FourInARowPerformanceAgent extends FourInARowAgent {

	/**
	 * Start state of any FourInARowGame to attempt a fresh search of the tree.
	 */
	private static int EMPTY_MOVE = 0;
	private static Piece[][] EMPTY_BOARD = new Piece[][] {
			{ Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___,
					Piece.__EMPTY___, Piece.__EMPTY___ },
			{ Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___,
					Piece.__EMPTY___, Piece.__EMPTY___ },
			{ Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___,
					Piece.__EMPTY___, Piece.__EMPTY___ },
			{ Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___,
					Piece.__EMPTY___, Piece.__EMPTY___ },
			{ Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___,
					Piece.__EMPTY___, Piece.__EMPTY___ },
			{ Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___,
					Piece.__EMPTY___, Piece.__EMPTY___ } };
	private static FourInARowGameState EMPTY_STATE = new FourInARowGameState(EMPTY_MOVE, EMPTY_BOARD, Piece.PLAYER_ONE,
			true);

	/**
	 * Easy win state of FourInARowGame to attempt see how quickly the Agent will
	 * identify the winning move.
	 */
	private static int ALMOST_MIDDLE_WIN_MOVE = 6;
	private static Piece[][] ALMOST_MIDDLE_WIN_BOARD = new Piece[][] {
			{ Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___,
					Piece.__EMPTY___, Piece.__EMPTY___ },
			{ Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___,
					Piece.__EMPTY___, Piece.__EMPTY___ },
			{ Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___,
					Piece.__EMPTY___, Piece.__EMPTY___ },
			{ Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.PLAYER_ONE, Piece.PLAYER_TWO,
					Piece.__EMPTY___, Piece.__EMPTY___ },
			{ Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.PLAYER_ONE, Piece.PLAYER_TWO,
					Piece.__EMPTY___, Piece.__EMPTY___ },
			{ Piece.__EMPTY___, Piece.__EMPTY___, Piece.__EMPTY___, Piece.PLAYER_ONE, Piece.PLAYER_TWO,
					Piece.__EMPTY___, Piece.__EMPTY___ } };
	private static FourInARowGameState ALMOST_MIDDLE_WIN_STATE = new FourInARowGameState(ALMOST_MIDDLE_WIN_MOVE,
			ALMOST_MIDDLE_WIN_BOARD, Piece.PLAYER_ONE, true);

	/**
	 * Number of iterations used in timing runs.
	 */
	private static int MAX_NUMBER_OF_ITERATION = 20;
	private static int MAX_DEPTH = 5;
	private static int MIN_DEPTH = 0;

	public FourInARowPerformanceAgent(FourInARowGameState gameState, FourInARowEvaluationFunction evaluator) {
		super(gameState, new FourInARowMoveGeneration(), evaluator);
	}

	/**
	 * Prints the performance data for a specific depth in easy to read string
	 * format.
	 * 
	 * @param depth
	 * @param average
	 * @param evaluatorString
	 */
	private static void printWithWords(int depth, long average, String evaluatorString) {
		System.out.println(
				"Depth: " + depth + ", Time: " + ((double) average / 100000000.0) + " seconds, " + evaluatorString);
	}

	/**
	 * Prints the performance data for a specific depth in CSV format.
	 * 
	 * @param depth
	 * @param average
	 * @param evaluatorCount
	 */
	private static void printCSV(int depth, long average, int evaluatorCount) {
		System.out.println(depth + ((double) average / 100000000.0) + evaluatorCount);
	}

	/**
	 * The main method to run to get a performance evaluation of the Agent.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FourInARowPerformanceEvaluationFunction evaluator = new FourInARowPerformanceEvaluationFunction();
		FourInARowAgent tester = new FourInARowPerformanceAgent(ALMOST_MIDDLE_WIN_STATE, evaluator);
		for (int depth = MIN_DEPTH; depth <= MAX_DEPTH; ++depth) {
			long average = 0;
			for (int iteration = 0; iteration < MAX_NUMBER_OF_ITERATION; ++iteration) {
				long time = System.nanoTime();
				tester.search(depth, true);
				time = System.nanoTime() - time;
				average += (time / MAX_NUMBER_OF_ITERATION);
				if (iteration != MAX_NUMBER_OF_ITERATION - 1) {
					evaluator.resetCounter();
				}
			}
			printWithWords(depth, average, evaluator.getCounterString());
			evaluator.resetCounter();
		}
	}

}
