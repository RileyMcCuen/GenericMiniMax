package minimax.fourinarow.core.arrays.core;

import utils.implementation.core.DeepCopy;

public class FourInARowDeepCopier implements DeepCopy<FourInARowGameState> {

	@Override
	public FourInARowGameState deepCopy(FourInARowGameState gameState) {
		return new FourInARowGameState(gameState.getPlyNumber(), gameState.getBoard(), gameState.getCurrentPlayer(),
				gameState.getNextOpenRowInColumns());
	}

}
