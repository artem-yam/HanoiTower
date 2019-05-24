package project.state.searcher.evaluation.cost;

import project.state.StateModel;
import project.utils.cost.HanoiCostCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class EqualPricesSearch extends CostTypeSearch {

    public EqualPricesSearch(HanoiCostCalculator costCalculator) {
        super(costCalculator);
    }

    public EqualPricesSearch() {
        super(new HanoiCostCalculator());
    }

    @Override
    public boolean search(StateModel currentState, int maxSteps) {

        statesWithCost = new TreeMap<>();

        List<StateModel> newStates = new ArrayList<>();
        newStates.add(currentState);
        searchTree.put(new byte[]{(byte) currentStep}, currentState);

        statesWithCost.put(0, newStates);

        while (!isFinished && currentStep < maxSteps) {

            newStates = generateNextStep(newStates);

        }

        return isFinished;
    }

    @Override
    protected List<StateModel> generateNextStep(List<StateModel> states) {
        currentStep++;

        List<StateModel> allStates = new ArrayList<>();

        for (StateModel state : states) {
            allStates.addAll(generateNewStates(state));
        }

        checkStates(allStates);

        return allStates;
    }

    private List<StateModel> generateNewStates(StateModel oldState) {

        List<StateModel> nextStates = generator.getAllNewPossibleStates(oldState);

        fillTree(searchTree, oldState, nextStates);

        List<StateModel> existingStates = new ArrayList<>();

        for (StateModel state : nextStates) {

            int stateCost = findStateCost(oldState) + costCalculator.getCost(
                    oldState, state);

            int stateLastCost = findStateCost(state);

            if (stateLastCost > stateCost) {
                statesWithCost.get(stateLastCost).remove(state);
            } else if (stateLastCost != COST_NOT_FOUND) {
                existingStates.add(state);
            }

            if (!existingStates.contains(state)) {
                if (statesWithCost.containsKey(stateCost)) {
                    statesWithCost.get(stateCost).add(state);
                } else {
                    List<StateModel> newStateList = new ArrayList<>();
                    newStateList.add(state);
                    statesWithCost.put(stateCost, newStateList);
                }
            }
        }

        nextStates.removeAll(existingStates);

        return nextStates;
    }

}
