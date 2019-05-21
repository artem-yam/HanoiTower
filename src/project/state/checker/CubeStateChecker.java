package project.state.checker;

import project.state.StateModel;

public class CubeStateChecker extends StateChecker {
    
    public CubeStateChecker(StateModel goalState) {
        super(goalState);
    }
    
    public CubeStateChecker(int disksCount) {
        super(StateModel.getWinState(disksCount));
    }
    
    @Override
    public boolean checkGoal(StateModel currentState) {
        return goalState.equals(currentState);
    }
}
