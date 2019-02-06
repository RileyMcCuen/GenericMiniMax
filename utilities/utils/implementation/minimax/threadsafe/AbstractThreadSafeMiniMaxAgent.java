package utils.implementation.minimax.threadsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import utils.implementation.AbstractGameState;
import utils.implementation.AbstractMove;
import utils.implementation.EvaluationFunction;
import utils.implementation.MoveGeneration;

public abstract class AbstractThreadSafeMiniMaxAgent<M extends AbstractMove, G extends AbstractGameState<M>>
		implements ThreadSafeMiniMaxAgent<M, G> {
	public static final int MINIMUM_DEPTH = 0;
	public static final int ALPHA_BEGINNING_VALUE = Integer.MIN_VALUE;
	public static final int BETA_BEGINNING_VALUE = Integer.MAX_VALUE;
	public static final long DEFAULT_DELAYMS = 5000;

	protected G gameState;
	protected MoveGeneration<M, G> moveGenerator;
	protected EvaluationFunction<G> evaluator;
	protected M bestMove = null;
	protected int maxDepth = 0;
	protected final ExecutorService xs = Executors.newSingleThreadScheduledExecutor();

	/**
	 * These values are passed to the evaluation function and if an evaluation is
	 * equal to one of these values then it is a terminal game state. Useful in
	 * checking when to end a search.
	 */
	protected int positiveTerminalEvaluation = 100;
	protected int negativeTerminalEvaluation = -100;

	/**
	 * 
	 * Instantiates an a MiniMaxAgent that has the default terminalEvaluation values
	 * of +/-100.
	 * 
	 * @param gameState
	 * @param moveGenerator
	 * @param evaluator
	 */
	protected AbstractThreadSafeMiniMaxAgent(G gameState, MoveGeneration<M, G> moveGenerator,
			EvaluationFunction<G> evaluator) {
		this.gameState = gameState;
		this.moveGenerator = moveGenerator;
		this.evaluator = evaluator;
	}

	/**
	 * 
	 * Instantiates an a MiniMaxAgent that has custom terminalEvaluation values.
	 * 
	 * @param gameState
	 * @param moveGenerator
	 * @param evaluator
	 */
	public AbstractThreadSafeMiniMaxAgent(G gameState, MoveGeneration<M, G> moveGenerator,
			EvaluationFunction<G> evaluator, int positiveTerminalEvaluation, int negativeTerminalEvaluation) {
		this.gameState = gameState;
		this.moveGenerator = moveGenerator;
		this.evaluator = evaluator;
		this.positiveTerminalEvaluation = positiveTerminalEvaluation;
		this.negativeTerminalEvaluation = negativeTerminalEvaluation;
	}

	/**
	 * 
	 * @return - group of moves that can be used to modify the game-state.
	 */
	private Iterable<M> getMoves() {
		return moveGenerator.generateMoves(gameState);
	};

	/**
	 * 
	 * @return - an evaluation for the gameState.
	 */
	private int evaluate() {
		return evaluator.evaluate(gameState, positiveTerminalEvaluation, negativeTerminalEvaluation);
	};

	/**
	 * 
	 * @param move - the move that will be used to manipulate the game-state.
	 */
	private void makeMove(M move) {
		gameState.makeMove(move);
	};

	/**
	 * 
	 * @param move - the move that will be used to undo a call to the makeMove()
	 *             function that used the same move the game-state.
	 */
	private void undoMove(M move) {
		gameState.undoMove(move);
	};

	/**
	 * 
	 * @return - provides what the Agent has decided to be the greatest move. Can
	 *         return null.
	 */
	public M getBestMove() {
		return bestMove;
	}

	/**
	 * Updates the gameState to the one given.
	 * 
	 * @param gameState
	 */
	public void setState(G gameState) {
		this.gameState = gameState;
	}

	/**
	 * Attempts to find the value minimizing move in the current game-state. Checks
	 * if it is interrupted at ever iteration. If it is it throws Exception.
	 * 
	 * @param depth - the current depth of the search.
	 * @param alpha - the current alpha value at this point in the search.
	 * @param beta  - the current beta value at this point in the search.
	 * @return - the minimum value of all potential moves.
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	private int findMin(G gameState, M bestMoveAtDepth, int depth, int alpha, int beta) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException("Stopped at depth: " + depth);
		}
		int evaluation = evaluate();
		if (depth == 0 || evaluation == positiveTerminalEvaluation || evaluation == negativeTerminalEvaluation) {
			return evaluation;
		}
		for (M move : getMoves()) {
			makeMove(move);
			move.setValue(findMax((G) gameState.deepCopy(true), bestMoveAtDepth, depth - 1, alpha, beta));
			undoMove(move);
			if (move.getValue() < beta) {
				beta = move.getValue();
			}
			if (alpha >= beta) {
				return Integer.MIN_VALUE;
			}
		}
		return beta;
	}

	/**
	 * Attempts to find the value maximizing move in the current game-state. Checks
	 * if it is interrupted at ever iteration. If it is it throws Exception.
	 * 
	 * @param depth - the current depth of the search.
	 * @param alpha - the current alpha value at this point in the search.
	 * @param beta  - the current beta value at this point in the search.
	 * @return - the maximum value of all potential moves.
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	private int findMax(G gameState, M bestMoveAtDepth, int depth, int alpha, int beta) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException("Stopped at depth: " + depth);
		}
		int evaluation = evaluate();
		if (depth == 0 || evaluation == positiveTerminalEvaluation || evaluation == negativeTerminalEvaluation) {
			return evaluation;
		}
		for (M move : getMoves()) {
			makeMove(move);
			move.setValue(findMin((G) gameState.deepCopy(true), bestMoveAtDepth, depth - 1, alpha, beta));
			undoMove(move);
			if (move.getValue() > alpha) {
				alpha = move.getValue();
			}
			if (alpha >= beta) {
				return Integer.MAX_VALUE;
			}
		}
		return alpha;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<M> search(int depth, boolean findMax) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}
		M bestMoveFound = null;
		G copyOfGameState = (G) gameState.deepCopy(true);
		if (findMax) {
			findMax(copyOfGameState, bestMoveFound, depth, ALPHA_BEGINNING_VALUE, BETA_BEGINNING_VALUE);
		} else {
			findMin(copyOfGameState, bestMoveFound, depth, ALPHA_BEGINNING_VALUE, BETA_BEGINNING_VALUE);
		}
		return null;
	}

	@Override
	public Iterable<M> searchMoveList(int minDepth, int maxDepth, boolean findMax) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<M> bestFirstSearchWithMoveList(int minDepth, int maxDepth, boolean findMax, Iterable<M> moves) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Can be used extract a string representation of the Agent. Can be helpful for
	 * debugging purposes or in a terminal based game. This method is not called by
	 * default and is not required for the Agent to perform properly.
	 */
	@Override
	public abstract String toString();

}
