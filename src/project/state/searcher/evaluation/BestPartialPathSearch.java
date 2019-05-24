package project.state.searcher.evaluation;


import project.state.StateModel;
import project.state.searcher.SearchInWidth;
import project.utils.evaluation.ClassicCubeEvaluationFunction;
import project.utils.evaluation.EvaluationComparator;

import java.util.ArrayList;
import java.util.List;

public class BestPartialPathSearch extends SearchInWidth {

    private ClassicCubeEvaluationFunction evaluation;
    private int partDepth;
    private int stepsDone;
    
    /*private int bestEvaluation = 0;
    private EvaluationFunction evaluation = new ClassicCubeEvaluationFunction();
    */

    public BestPartialPathSearch(ClassicCubeEvaluationFunction evaluation, int partDepth) {
        this.evaluation = evaluation;
        this.partDepth = partDepth;
    }

    public BestPartialPathSearch(int towersCount, int disksCount, int partDepth) {
        this.partDepth = partDepth;
        evaluation = new ClassicCubeEvaluationFunction(towersCount, disksCount);
    }

    @Override
    public boolean search(StateModel currentState, int maxSteps) {
        List<StateModel> newStates = new ArrayList<>();
        newStates.add(currentState);

        searchTree.put(new byte[]{(byte) currentStep}, currentState);

        recursiveSearch(newStates, maxSteps);

        return isFinished;
    }

    private void recursiveSearch(List<StateModel> states, int maxSteps) {

        if (!isFinished && currentStep < maxSteps) {
            states.sort(new EvaluationComparator<>(evaluation));
            
            /*int stepBestEvaluation = evaluation.calculate(states.get(0));
            if (stepBestEvaluation >= bestEvaluation) {
                bestEvaluation = stepBestEvaluation;
            } else {
                return;
            }*/

            for (int i = 0; i < states.size() && !isFinished; i++) {
                List<StateModel> newStates = partialWidthSearch(states.get(i),
                        partDepth, maxSteps);

                //bestEvaluation = evaluation.calculate(states.get(i));

                recursiveSearch(newStates, maxSteps);
                currentStep -= stepsDone;
            }

        }
    }

    private List<StateModel> partialWidthSearch(StateModel currentState,
                                                int maxPartialSteps, int maxSteps) {
        stepsDone = 0;

        List<StateModel> newStates = new ArrayList<>();
        newStates.add(currentState);

        while (!isFinished && stepsDone < maxPartialSteps &&
                currentStep < maxSteps) {

            stepsDone++;

            newStates = generateNextStep(newStates);

        }

        return newStates;
    }
}
