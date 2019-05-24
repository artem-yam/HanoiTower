package project.state.searcher.evaluation;


import project.state.StateModel;
import project.state.searcher.SearchInDepth;
import project.utils.evaluation.ClassicCubeEvaluationFunction;
import project.utils.evaluation.EvaluationComparator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GradientSearch extends SearchInDepth {

    private ClassicCubeEvaluationFunction evaluation;

    public GradientSearch(ClassicCubeEvaluationFunction evaluation) {
        this.evaluation = evaluation;
    }

    public GradientSearch(int towersCount, int disksCount) {
        evaluation = new ClassicCubeEvaluationFunction(towersCount, disksCount);
    }

    @Override
    public boolean search(StateModel currentState, int maxSteps) {
        visitedStates = new HashSet<>();

        searchTree.put(new byte[]{(byte) currentStep}, currentState);
        visitedStates.add(currentState);

        return recursiveSearch(currentState, maxSteps);
    }

    private boolean recursiveSearch(StateModel currentState, int maxSteps) {
        List<StateModel> newStates = new ArrayList<>(
                generator.getAllNewPossibleStates(currentState));
        newStates.sort(new EvaluationComparator<>(evaluation));

        for (StateModel state : newStates) {
            if (++currentStep <= maxSteps) {

                checkStateAndChildren(currentState, state, maxSteps);

                if (isFinished) {
                    return true;
                }

            } else {
                currentStep--;
                return isFinished;
            }
        }

        return isFinished;
    }

}
