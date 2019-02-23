package utils.implementation.minimax.threadsafe;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import utils.implementation.core.AbstractGameState;
import utils.implementation.core.AbstractMove;
import utils.implementation.core.DeepCopy;
import utils.implementation.core.EvaluationFunction;
import utils.implementation.core.MoveGeneration;
import utils.implementation.core.MutableMove;

public abstract class AbstractThreadSafeMiniMaxAgent<M extends AbstractMove, G extends AbstractGameState<M>>
		implements ThreadSafeMiniMaxAgent<M, G> {
	public static final int MINIMUM_DEPTH = 0;
	public static final int ALPHA_BEGINNING_VALUE = Integer.MIN_VALUE;
	public static final int BETA_BEGINNING_VALUE = Integer.MAX_VALUE;
	public static final long DEFAULT_SEARCH_TIME_MS = 5000;

	protected DeepCopy<G> copier;
	protected MoveGeneration<M, G> moveGenerator;
	protected EvaluationFunction<G> evaluator;
	protected final ExecutorService xs = Executors.newSingleThreadScheduledExecutor();
	protected M bestMove = null;

	/**
	 * These values are passed to the evaluation function and if an evaluation is
	 * equal to one of these values then it is a terminal game state. Useful in
	 * checking when to end a search.
	 */
	protected int positiveTerminalEvaluation = 100;
	protected int negativeTerminalEvaluation = -100;

	/**
	 * 
	 * Instantiates a MiniMaxAgent that has the default terminalEvaluation values of
	 * +/-100.
	 * 
	 * @param copier
	 * @param moveGenerator
	 * @param evaluator
	 */
	protected AbstractThreadSafeMiniMaxAgent(DeepCopy<G> copier, MoveGeneration<M, G> moveGenerator,
			EvaluationFunction<G> evaluator) {
		this.copier = copier;
		this.moveGenerator = moveGenerator;
		this.evaluator = evaluator;
	}

	/**
	 * 
	 * Instantiates a MiniMaxAgent that has custom terminalEvaluation values.
	 * 
	 * @param copier
	 * @param moveGenerator
	 * @param evaluator
	 */
	public AbstractThreadSafeMiniMaxAgent(DeepCopy<G> copier, MoveGeneration<M, G> moveGenerator,
			EvaluationFunction<G> evaluator, int positiveTerminalEvaluation, int negativeTerminalEvaluation) {
		this.copier = copier;
		this.moveGenerator = moveGenerator;
		this.evaluator = evaluator;
		this.positiveTerminalEvaluation = positiveTerminalEvaluation;
		this.negativeTerminalEvaluation = negativeTerminalEvaluation;
	}

	/**
	 * 
	 * @return - group of moves that can be used to modify the game-state.
	 */
	private Iterable<M> getMoves(G gameState) {
		return moveGenerator.generateMoves(gameState);
	};

	/**
	 * 
	 * @return - an evaluation for the gameState.
	 */
	private int evaluate(G gameState) {
		return evaluator.evaluate(gameState, positiveTerminalEvaluation, negativeTerminalEvaluation);
	};

	/**
	 * 
	 * @param move - the move that will be used to manipulate the game-state.
	 */
	private G makeMove(G gameState, M move) {
		gameState.makeMove(move);
		return gameState;
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
	 * Attempts to find the value minimizing move in the current game-state. Checks
	 * if it is interrupted at ever iteration. If it is it throws Exception.
	 * 
	 * @param depth - the current depth of the search.
	 * @param alpha - the current alpha value at this point in the search.
	 * @param beta  - the current beta value at this point in the search.
	 * @return - the minimum value of all potential moves.
	 * @throws InterruptedException
	 */
	private int findMin(G gameState, MutableMove<M> bestMove, int depth, int maxDepth, int alpha, int beta)
			throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}
		int evaluation = evaluate(gameState);
		if (depth == maxDepth || evaluation == positiveTerminalEvaluation || evaluation == negativeTerminalEvaluation) {
			return evaluation;
		}
		for (M move : getMoves(gameState)) {
			move.setValue(
					findMax(makeMove(copier.deepCopy(gameState), move), bestMove, depth + 1, maxDepth, alpha, beta));
			if (move.getValue() < beta) {
				beta = move.getValue();
				if (depth == 0) {
					bestMove.setMove(move);
				}
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
	private int findMax(G gameState, MutableMove<M> bestMove, int depth, int maxDepth, int alpha, int beta)
			throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}
		int evaluation = evaluate(gameState);
		if (depth == maxDepth || evaluation == positiveTerminalEvaluation || evaluation == negativeTerminalEvaluation) {
			return evaluation;
		}
		for (M move : getMoves(gameState)) {
			move.setValue(
					findMin(makeMove(copier.deepCopy(gameState), move), bestMove, depth + 1, maxDepth, alpha, beta));
			if (move.getValue() > alpha) {
				alpha = move.getValue();
				if (depth == 0) {
					bestMove.setMove(move);
				}
			}
			if (alpha >= beta) {
				return Integer.MAX_VALUE;
			}
		}
		return alpha;
	}

	@Override
	public M search(G gameState, int depth, boolean findMax) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}
		MutableMove<M> move = new MutableMove<>();
		if (findMax) {
			findMax(gameState, move, 0, depth, ALPHA_BEGINNING_VALUE, BETA_BEGINNING_VALUE);
		} else {
			findMin(gameState, move, 0, depth, ALPHA_BEGINNING_VALUE, BETA_BEGINNING_VALUE);
		}
		return move.getMove();
	}

	@Override
	public M iterativeSearch(G gameState, int minDepth, int maxDepth, boolean findMax, long time) {
		long timeLeft = time;
		int depth = minDepth;
		while (depth++ < maxDepth) {
			int _depth = depth;
			long startTime = System.currentTimeMillis();
			Callable<M> search = new Callable<M>() {

				@Override
				public M call() throws Exception {
					return search(copier.deepCopy(gameState), _depth, findMax);
				}

			};
			Future<M> searchResult = xs.submit(search);
			try {
				M move = searchResult.get(timeLeft, TimeUnit.MILLISECONDS);
				this.bestMove = move;
				long timeTaken = System.currentTimeMillis() - startTime;
				timeLeft -= timeTaken;
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				return getBestMove();
			}
		}
		return getBestMove();
	}

	/**
	 * Can be used extract a string representation of the Agent. Can be helpful for
	 * debugging purposes or in a terminal based game. This method is not called by
	 * default and is not required for the Agent to perform properly.
	 */
	@Override
	public abstract String toString();
}
