package project.utils.cost;

import project.state.StateModel;

public class HanoiCostCalculator {

    private static int DEFAULT_COST = 1;

    public int getCost(StateModel oldState, StateModel newState) {
        return DEFAULT_COST;
    }

}
