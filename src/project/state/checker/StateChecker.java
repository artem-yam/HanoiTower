package project.state.checker;

import project.state.StateModel;

public abstract class StateChecker {
    
    protected StateModel goalState;
    
    protected StateChecker(StateModel goalState) {
        this.goalState = goalState;
    }
    
    public StateModel getGoalState() {
        return goalState;
    }
    
    public void setGoalState(StateModel goalState) {
        this.goalState = goalState;
    }
    
    public abstract boolean checkGoal(StateModel modelState);
}
