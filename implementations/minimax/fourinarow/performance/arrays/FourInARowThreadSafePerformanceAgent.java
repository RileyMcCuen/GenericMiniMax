package minimax.fourinarow.performance.arrays;

import minimax.fourinarow.core.arrays.agents.FourInARowThreadSafeAgent;
import minimax.fourinarow.core.arrays.core.FourInARowDeepCopier;
import minimax.fourinarow.core.arrays.core.FourInARowEvaluationFunction;
import minimax.fourinarow.core.arrays.core.FourInARowGameState;
import minimax.fourinarow.core.arrays.core.FourInARowMove;
import minimax.fourinarow.core.arrays.core.FourInARowMoveGeneration;
import utils.implementation.core.EvaluationFunction;
import utils.implementation.core.MoveGeneration;

public class FourInARowThreadSafePerformanceAgent extends FourInARowThreadSafeAgent {

	public FourInARowThreadSafePerformanceAgent(EvaluationFunction<FourInARowGameState> evaluator) {
		super(new FourInARowDeepCopier(), new FourInARowMoveGeneration(), evaluator);
	}

	public FourInARowThreadSafePerformanceAgent(MoveGeneration<FourInARowMove, FourInARowGameState> moveGenerator) {
		super(new FourInARowDeepCopier(), moveGenerator, new FourInARowEvaluationFunction());
	}
}
