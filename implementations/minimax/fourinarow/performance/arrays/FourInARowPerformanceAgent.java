package minimax.fourinarow.performance.arrays;

import minimax.fourinarow.core.arrays.FourInARowAgent;
import minimax.fourinarow.core.arrays.core.FourInARowEvaluationFunction;
import minimax.fourinarow.core.arrays.core.FourInARowGameState;
import minimax.fourinarow.core.arrays.core.FourInARowMove;
import minimax.fourinarow.core.arrays.core.FourInARowMoveGeneration;
import minimax.fourinarow.core.arrays.core.Piece;
import utils.implementation.core.EvaluationFunction;
import utils.implementation.core.MoveGeneration;
import utils.implementation.minimax.notthreadsafe.AbstractMiniMaxAgent;
import utils.performance.PerformanceEvaluationFunction;
import utils.performance.PrintUtilities;
import utils.performance.TimingUtilities;

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
	public static FourInARowGameState EMPTY_STATE = new FourInARowGameState(EMPTY_MOVE, EMPTY_BOARD, Piece.PLAYER_ONE,
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
	public static FourInARowGameState ALMOST_MIDDLE_WIN_STATE = new FourInARowGameState(ALMOST_MIDDLE_WIN_MOVE,
			ALMOST_MIDDLE_WIN_BOARD, Piece.PLAYER_ONE, true);

	/**
	 * Number of iterations used in timing runs.
	 */
	private static int MAX_NUMBER_OF_ITERATION = 20;
	private static int MAX_DEPTH = 40;
	private static int MIN_DEPTH = 0;

	public FourInARowPerformanceAgent(FourInARowGameState gameState,
			EvaluationFunction<FourInARowGameState> evaluator) {
		super(gameState, new FourInARowMoveGeneration(), evaluator);
	}

	public FourInARowPerformanceAgent(FourInARowGameState gameState,
			MoveGeneration<FourInARowMove, FourInARowGameState> moveGenerator) {
		super(gameState, moveGenerator, new FourInARowEvaluationFunction());
	}

	/**
	 * The main method to run to get a performance evaluation of the Agent.
	 * 
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		PerformanceEvaluationFunction<FourInARowGameState> evaluator = new PerformanceEvaluationFunction<FourInARowGameState>(
				new FourInARowEvaluationFunction());
		FourInARowAgent tester = new FourInARowPerformanceAgent(EMPTY_STATE.deepCopy(true), evaluator);
		TimingUtilities<FourInARowMove, FourInARowGameState> timer = new TimingUtilities<FourInARowMove, FourInARowGameState>();
		evaluator.resetCounter();
		long time = timer.averageTimedEvaluationIterativeMultipleDepths(1, tester, 2, 12, true);
		PrintUtilities.printWithWords(15, time, evaluator.getCounterString());
		System.out.println(tester.getBestMove().toString());
		System.exit(0);
	}

}
