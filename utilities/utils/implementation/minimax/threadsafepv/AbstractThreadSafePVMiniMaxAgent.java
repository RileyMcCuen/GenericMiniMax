package utils.implementation.minimax.threadsafepv;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import utils.datastructures.stack.Stack;
import utils.implementation.core.AbstractGameState;
import utils.implementation.core.AbstractMove;
import utils.implementation.core.EvaluationFunction;

public abstract class AbstractThreadSafePVMiniMaxAgent<M extends AbstractMove, G extends AbstractGameState<M>>
		implements ThreadSafePVMiniMaxAgent<M, G> {
	public static final int MINIMUM_DEPTH = 0;
	public static final int ALPHA_BEGINNING_VALUE = Integer.MIN_VALUE;
	public static final int BETA_BEGINNING_VALUE = Integer.MAX_VALUE;
	public static final long DEFAULT_SEARCH_TIME_MS = 5000;

	protected G gameState;
	protected MoveGenerationWithPV<M, G> moveGenerator;
	protected EvaluationFunction<G> evaluator;
	protected final ExecutorService xs = Executors.newSingleThreadScheduledExecutor();
	protected Stack<M> bestPath = null;

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
	protected AbstractThreadSafePVMiniMaxAgent(G gameState, MoveGenerationWithPV<M, G> moveGenerator,
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
	public AbstractThreadSafePVMiniMaxAgent(G gameState, MoveGenerationWithPV<M, G> moveGenerator,
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
	private Stack<M> getMoves(M pvMove) {
		return moveGenerator.generateMoves(gameState, pvMove);
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
		return bestPath.peekMove();
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
	 * 
	 * Checks all terminal search conditions.
	 * 
	 * @param depth      - current search depth.
	 * @param evaluation - evaluation of game-state.
	 * @return - whether to end current search or not.
	 */
	private boolean shouldEndSearch(int depth, int evaluation) {
		return depth == 0 || evaluation == positiveTerminalEvaluation || evaluation == negativeTerminalEvaluation;
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
	private int findMin(G gameState, Stack<M> movePath, Stack<M> startPath, int depth, int alpha, int beta)
			throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException("Stopped at depth: " + depth);
		}
		int evaluation = evaluate();
		if (shouldEndSearch(depth, evaluation)) {
			return evaluation;
		}
		Stack<M> bestStack = null;
		for (M move : getMoves(startPath.popMove())) {
			Stack<M> passStack = new Stack<M>();
			makeMove(move);
			move.setValue(findMax((G) gameState.deepCopy(true), passStack, startPath, depth - 1, alpha, beta));
			undoMove(move);
			if (move.getValue() < beta) {
				beta = move.getValue();
				bestStack = passStack;
			}
			if (alpha >= beta) {
				return Integer.MIN_VALUE;
			}
		}
		movePath.swapStack(bestStack);
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
	private int findMax(G gameState, Stack<M> movePath, Stack<M> startPath, int depth, int alpha, int beta)
			throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException("Stopped at depth: " + depth);
		}
		int evaluation = evaluate();
		if (shouldEndSearch(depth, evaluation)) {
			return evaluation;
		}
		Stack<M> bestStack = null;
		for (M move : getMoves(startPath.popMove())) {
			Stack<M> passStack = new Stack<M>(move);
			makeMove(move);
			move.setValue(findMin((G) gameState.deepCopy(true), passStack, startPath, depth - 1, alpha, beta));
			undoMove(move);
			if (move.getValue() > alpha) {
				alpha = move.getValue();
				bestStack = passStack;
			}
			if (alpha >= beta) {
				return Integer.MAX_VALUE;
			}
		}
		movePath.swapStack(bestStack);
		return alpha;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Stack<M> searchWithPV(int depth, boolean findMax, Stack<M> startPath) throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}
		Stack<M> bestPath = new Stack<M>();
		G copyOfGameState = (G) gameState.deepCopy(true);
		if (findMax) {
			findMax(copyOfGameState, bestPath, startPath, depth, ALPHA_BEGINNING_VALUE, BETA_BEGINNING_VALUE);
		} else {
			findMin(copyOfGameState, bestPath, startPath, depth, ALPHA_BEGINNING_VALUE, BETA_BEGINNING_VALUE);
		}
		return bestPath;
	}

	@Override
	public M iterativeSearchWithPV(int minDepth, int maxDepth, boolean findMax, Stack<M> startPath, long time) {
		long timeLeft = time;
		int depth = minDepth;
		while(depth++ <= maxDepth) {
			int _depth = depth;
			long startTime = System.nanoTime();
			Callable<Stack<M>> search = new Callable<Stack<M>>() {
				
				@Override
				public Stack<M> call() throws Exception {
					return searchWithPV(_depth, findMax, startPath);
				}
				
			};
			Future<Stack<M>> searchResult = xs.submit(search);
			try {
				bestPath = searchResult.get(timeLeft, TimeUnit.MILLISECONDS);
				timeLeft -= (System.nanoTime() - startTime);
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				e.printStackTrace();
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
