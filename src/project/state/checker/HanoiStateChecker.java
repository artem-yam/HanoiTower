package project.state.checker;

import project.state.StateModel;

public class HanoiStateChecker extends StateChecker {
    
    public HanoiStateChecker(StateModel goalState) {
        super(goalState);
    }
    
    public HanoiStateChecker(int towersCount, int disksCount) {
        super(StateModel.getWinState(towersCount, disksCount));
    }
    
    @Override
    public boolean checkGoal(StateModel currentState) {
        return goalState.equals(currentState);
    }
}
