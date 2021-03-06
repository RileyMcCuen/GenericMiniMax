package utils.implementation.minimax.notthreadsafe;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import utils.implementation.core.AbstractGameState;
import utils.implementation.core.AbstractMove;
import utils.implementation.core.EvaluationFunction;
import utils.implementation.core.MoveGeneration;

/**
 * 
 * If this Agent is extended provided with all necessary components it will have
 * the capability to search the game-tree any game suited to using the MiniMax
 * algorithm. This is not the fastest implementation because of all the objects
 * used, however, this search algorithm does use alpha-beta pruning drastically
 * increasing performance.
 * 
 * @author Riley McCuen
 *
 * @param <M> The implementation of AbstractMove for the game that this
 *        game-state represents.
 * @param <G> The implementation of GameState for the game that this game-state
 *        represents.
 */

public abstract class AbstractMiniMaxAgent<M extends AbstractMove, G extends AbstractGameState<M>> extends Object
		implements MiniMaxAgent<M> {

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
	protected AbstractMiniMaxAgent(G gameState, MoveGeneration<M, G> moveGenerator, EvaluationFunction<G> evaluator) {
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
	public AbstractMiniMaxAgent(G gameState, MoveGeneration<M, G> moveGenerator, EvaluationFunction<G> evaluator,
			int positiveTerminalEvaluation, int negativeTerminalEvaluation) {
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
	 * Attempts to find the value minimizing move in the current game-state.
	 * 
	 * @param depth - the current depth of the search.
	 * @param alpha - the current alpha value at this point in the search.
	 * @param beta  - the current beta value at this point in the search.
	 * @return - the minimum value of all potential moves.
	 */
	private int findMin(int depth, int alpha, int beta) {
		int evaluation = evaluate();
		if (depth == maxDepth || evaluation == positiveTerminalEvaluation || evaluation == negativeTerminalEvaluation) {
			return evaluation;
		}
		for (M move : getMoves()) {
			makeMove(move);
			move.setValue(findMax(depth + 1, alpha, beta));
			undoMove(move);
			if (move.getValue() < beta) {
				beta = move.getValue();
				if (depth == MINIMUM_DEPTH) {
					bestMove = move;
				}
			}
			if (depth != MINIMUM_DEPTH && alpha >= beta) {
				return Integer.MIN_VALUE;
			}
		}
		return beta;
	}

	/**
	 * Attempts to find the value maximizing move in the current game-state.
	 * 
	 * @param depth - the current depth of the search.
	 * @param alpha - the current alpha value at this point in the search.
	 * @param beta  - the current beta value at this point in the search.
	 * @return - the maximum value of all potential moves.
	 */
	private int findMax(int depth, int alpha, int beta) {
		int evaluation = evaluate();
		if (depth == maxDepth || evaluation == positiveTerminalEvaluation || evaluation == negativeTerminalEvaluation) {
			return evaluation;
		}
		for (M move : getMoves()) {
			makeMove(move);
			move.setValue(findMin(depth + 1, alpha, beta));
			undoMove(move);
			if (move.getValue() > alpha) {
				alpha = move.getValue();
				if (depth == MINIMUM_DEPTH) {
					bestMove = move;
				}
			}
			if (depth != MINIMUM_DEPTH && alpha >= beta) {
				return Integer.MAX_VALUE;
			}
		}
		return alpha;
	}

	@Override
	public M search(int depth, boolean findMax) {
		maxDepth = depth;
		if (findMax) {
			findMax(MINIMUM_DEPTH, ALPHA_BEGINNING_VALUE, BETA_BEGINNING_VALUE);
		} else {
			findMin(MINIMUM_DEPTH, ALPHA_BEGINNING_VALUE, BETA_BEGINNING_VALUE);
		}
		return getBestMove();
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
	private int findMinInterruptable(int depth, int alpha, int beta) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException("Stopped at depth: " + depth);
		}
		int evaluation = evaluate();
		if (depth == maxDepth || evaluation == positiveTerminalEvaluation || evaluation == negativeTerminalEvaluation) {
			return evaluation;
		}
		for (M move : getMoves()) {
			makeMove(move);
			move.setValue(findMaxInterruptable(depth + 1, alpha, beta));
			undoMove(move);
			if (move.getValue() < beta) {
				beta = move.getValue();
				if (depth == MINIMUM_DEPTH) {
					bestMove = move;
				}
			}
			if (depth != MINIMUM_DEPTH && alpha >= beta) {
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
	private int findMaxInterruptable(int depth, int alpha, int beta) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException("Stopped at depth: " + depth);
		}
		int evaluation = evaluate();
		if (depth == maxDepth || evaluation == positiveTerminalEvaluation || evaluation == negativeTerminalEvaluation) {
			return evaluation;
		}
		for (M move : getMoves()) {
			makeMove(move);
			move.setValue(findMinInterruptable(depth + 1, alpha, beta));
			undoMove(move);
			if (move.getValue() > alpha) {
				alpha = move.getValue();
				if (depth == MINIMUM_DEPTH) {
					bestMove = move;
				}
			}
			if (depth != MINIMUM_DEPTH && alpha >= beta) {
				return Integer.MAX_VALUE;
			}
		}
		return alpha;
	}

	/**
	 * Runs a single search that can be interrupted if it goes over time.
	 * 
	 * @param depth
	 * @param findMax
	 * @return
	 * @throws InterruptedException
	 */
	private M searchInterruptable(int depth, boolean findMax) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			return getBestMove();
		}
		maxDepth = depth;
		if (findMax) {
			findMaxInterruptable(MINIMUM_DEPTH, ALPHA_BEGINNING_VALUE, BETA_BEGINNING_VALUE);
		} else {
			findMinInterruptable(MINIMUM_DEPTH, ALPHA_BEGINNING_VALUE, BETA_BEGINNING_VALUE);
		}
		return getBestMove();
	}

	@Override
	public M searchIterativeDeepening(int minDepth, int maxDepth, boolean findMax, long time) {
		Callable<M> iterativeSearch = new Callable<M>() {
			long startTime = System.currentTimeMillis();
			long timeLeft = 0;
			@Override
			public M call() throws Exception {
				M bestMoveSoFar = null;
				for (int depth = minDepth; depth <= maxDepth; ++depth) {
					try {
						M deeperMove = searchInterruptable(depth, findMax);
						bestMoveSoFar = deeperMove;
						timeLeft = (DEFAULT_DELAYMS - (System.currentTimeMillis() - startTime));
					} catch (InterruptedException e) {
						return bestMoveSoFar;
					}
				}
				return bestMoveSoFar;
			}

		};
		Future<M> futureMove = xs.submit(iterativeSearch);

		try {
			futureMove.get(time, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			futureMove.cancel(true);
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
