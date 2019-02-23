package minimax.fourinarow.performance.arrays;

import minimax.fourinarow.core.arrays.agents.FourInARowThreadSafePVAgent;
import minimax.fourinarow.core.arrays.core.FourInARowDeepCopier;
import minimax.fourinarow.core.arrays.core.FourInARowEvaluationFunction;
import minimax.fourinarow.core.arrays.core.FourInARowGameState;
import minimax.fourinarow.core.arrays.core.FourInARowMove;
import minimax.fourinarow.core.arrays.core.FourInARowMoveGenerationWithPV;
import minimax.fourinarow.core.arrays.core.Piece;
import utils.datastructures.stack.Stack;
import utils.implementation.core.EvaluationFunction;
import utils.implementation.minimax.threadsafepv.MoveGenerationWithPV;
import utils.performance.PerformanceEvaluationFunction;
import utils.performance.PrintUtilities;
import utils.performance.TimingUtilities;

public class FourInARowThreadSafePVPerformanceAgent extends FourInARowThreadSafePVAgent {

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
	public static FourInARowGameState EMPTY_STATE = new FourInARowGameState(EMPTY_MOVE, EMPTY_BOARD, Piece.PLAYER_ONE);

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
			ALMOST_MIDDLE_WIN_BOARD, Piece.PLAYER_ONE);

	/**
	 * Number of iterations used in timing runs.
	 */
	private static int MAX_NUMBER_OF_ITERATION = 20;
	private static int MAX_DEPTH = 40;
	private static int MIN_DEPTH = 0;

	public FourInARowThreadSafePVPerformanceAgent(FourInARowGameState gameState,
			EvaluationFunction<FourInARowGameState> evaluator) {
		super(gameState, new FourInARowDeepCopier(), new FourInARowMoveGenerationWithPV(), evaluator);
	}

	public FourInARowThreadSafePVPerformanceAgent(FourInARowGameState gameState,
			MoveGenerationWithPV<FourInARowMove, FourInARowGameState> moveGenerator) {
		super(gameState, new FourInARowDeepCopier(), moveGenerator, new FourInARowEvaluationFunction());
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
		FourInARowThreadSafePVPerformanceAgent tester = new FourInARowThreadSafePVPerformanceAgent(
				EMPTY_STATE, evaluator);
		long time = System.nanoTime();
		tester.iterativeSearchWithPV(2, 15, true, new Stack<FourInARowMove>(), 100000);
		time = System.nanoTime() - time;
		PrintUtilities.printWithWords(15, time, evaluator.getCounterString());
		System.out.println(tester.getBestMove().toString());
		for (FourInARowMove m : tester.bestPath) {
			System.out.println(m.toString());
		}
	}
}
