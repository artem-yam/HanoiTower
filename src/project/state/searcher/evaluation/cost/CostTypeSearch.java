package project.state.searcher.evaluation.cost;

import project.state.StateModel;
import project.state.searcher.SearchInWidth;
import project.utils.cost.HanoiCostCalculator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class CostTypeSearch extends SearchInWidth {

    protected final static int COST_NOT_FOUND = -1;

    protected HanoiCostCalculator costCalculator;
    protected Map<Integer, List<StateModel>> statesWithCost;

    protected CostTypeSearch(HanoiCostCalculator costCalculator) {
        this.costCalculator = costCalculator;
    }

    protected int findStateCost(StateModel state) {
        int cost = COST_NOT_FOUND;
        Iterator<Map.Entry<Integer, List<StateModel>>> iter =
                statesWithCost.entrySet().iterator();

        while (iter.hasNext() && cost == -1) {
            Map.Entry<Integer, List<StateModel>> entrySet = iter.next();
            if (entrySet.getValue().contains(state)) {
                cost = entrySet.getKey();
            }

        }

        return cost;
    }

    @Override
    public abstract boolean search(StateModel currentState, int maxSteps);

}
