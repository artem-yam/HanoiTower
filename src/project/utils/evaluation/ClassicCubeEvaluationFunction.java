package project.utils.evaluation;

import project.state.StateModel;

public class ClassicCubeEvaluationFunction {

    private StateModel goalState;


    public ClassicCubeEvaluationFunction(int towersCount, int disksCount) {
        this.goalState = new StateModel(towersCount, disksCount);
    }


    public int calculate(StateModel state) {
        int evaluation = 0;

        for (int i = 0; i < state.getTowers().size(); i++) {
            int maxEval = goalState.getTowers().get(i).getRod().size();

            evaluation += maxEval - Math.abs(maxEval - state.getTowers().get(i).getRod().size());
        }

        return evaluation;
    }

}
