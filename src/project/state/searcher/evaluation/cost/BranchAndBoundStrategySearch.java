package project.state.searcher.evaluation.cost;

import project.state.StateModel;
import project.utils.cost.HanoiCostCalculator;

import java.util.*;

public class BranchAndBoundStrategySearch extends CostTypeSearch {

    private Map<StateModel, Integer> statesWithStepsToReach;

    public BranchAndBoundStrategySearch(HanoiCostCalculator costCalculator) {
        super(costCalculator);
    }

    public BranchAndBoundStrategySearch() {
        super(new HanoiCostCalculator());
    }

    @Override
    public boolean search(StateModel currentState, int maxSteps) {

        statesWithCost = new TreeMap<>();
        statesWithStepsToReach = new HashMap<>();

        List<StateModel> newStates = new ArrayList<>();
        newStates.add(currentState);
        searchTree.put(new byte[]{(byte) currentStep}, currentState);

        statesWithCost.put(0, newStates);
        statesWithStepsToReach.put(currentState, 0);

        boolean continueSearch = true;

        while (!isFinished && continueSearch) {

            for (List<StateModel> states : statesWithCost.values()) {

                if (!states.isEmpty()) {
                    generateNewStates(states.get(0), maxSteps);
                    break;
                }

            }

            for (List<StateModel> states : statesWithCost.values()) {
                continueSearch = !states.isEmpty();
                if (continueSearch) {
                    break;
                }
            }

        }

        return isFinished;
    }

    private void generateNewStates(StateModel oldState, int maxSteps) {
        currentStep = statesWithStepsToReach.get(oldState) + 1;

        if (currentStep <= maxSteps) {

            List<StateModel> nextStates = generator.getAllNewPossibleStates(
                    oldState);

            for (StateModel nextState : nextStates) {
                statesWithStepsToReach.put(nextState, currentStep);

                int nextStateCost = findStateCost(oldState) +
                        costCalculator.getCost(oldState,
                                nextState);

                int stateLastCost = findStateCost(nextState);

                if (stateLastCost > nextStateCost) {
                    statesWithCost.get(stateLastCost).remove(nextState);
                }

                if (statesWithCost.containsKey(nextStateCost)) {
                    statesWithCost.get(nextStateCost).add(nextState);
                } else {
                    List<StateModel> newStateList = new ArrayList<>();
                    newStateList.add(nextState);
                    statesWithCost.put(nextStateCost, newStateList);
                }

            }

            fillTree(searchTree, oldState, nextStates);

            checkStates(nextStates);

        }
        for (List<StateModel> states : statesWithCost.values()) {
            states.remove(oldState);
        }
    }

}
