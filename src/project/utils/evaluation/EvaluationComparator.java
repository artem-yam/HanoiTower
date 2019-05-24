package project.utils.evaluation;

import project.state.StateModel;

import java.util.Comparator;

public class EvaluationComparator<T extends StateModel> implements Comparator<T> {

    private ClassicCubeEvaluationFunction evaluation;

    public EvaluationComparator(int towersCount, int disksCount) {
        evaluation = new ClassicCubeEvaluationFunction(towersCount, disksCount);
    }

    public EvaluationComparator(ClassicCubeEvaluationFunction evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public int compare(T o1, T o2) {
        int result = 0;

        if (evaluation.calculate(o1) > evaluation.calculate(o2)) {
            result = 1;
        } else if (evaluation.calculate(o1) < evaluation.calculate(o2)) {
            result = -1;
        }

        return result;
    }
}
