package project;

import project.action.Action;
import project.state.StateModel;
import project.state.checker.HanoiStateChecker;
import project.state.checker.StateChecker;
import project.state.generator.StatesGenerator;
import project.state.searcher.SearchEngine;
import project.state.searcher.SearchInDepth;
import project.state.searcher.SearchInWidth;
import project.state.searcher.evaluation.BestPartialPathSearch;
import project.state.searcher.evaluation.GradientSearch;
import project.state.searcher.evaluation.cost.BranchAndBoundStrategySearch;
import project.state.searcher.evaluation.cost.EqualPricesSearch;

import java.util.Date;

public class Runner {

    public static void main(String[] args) {
        int towersCount = 3;
        int disksCount = 8;


        StateChecker checker = new HanoiStateChecker(
                new StateModel(towersCount, disksCount));
        StatesGenerator generator = new StatesGenerator();

        StateModel model = new StateModel(towersCount, disksCount);
        model.getCurrentSituation();

        //System.out.println(checker.checkGoal(model));

        model = generator.getNewState(model, new Action(0, 1));
        model = generator.getNewState(model, new Action(0, 2));
        model.getCurrentSituation();

        Solver cuber = new Solver(checker, generator);
        SearchEngine widthSearcher = new SearchInWidth();
        SearchEngine depthSearcher = new SearchInDepth();
        SearchEngine gradientSearcher = new GradientSearch(towersCount, disksCount);
        SearchEngine partialSearcher = new BestPartialPathSearch(towersCount, disksCount, 2);
        SearchEngine branchAndBoundSearcher =
                new BranchAndBoundStrategySearch();
        SearchEngine equalPricesSearcher = new EqualPricesSearch();

        int stepsToCheck = 4;

        checkModel(cuber, widthSearcher, model, stepsToCheck);
        checkModel(cuber, depthSearcher, model, stepsToCheck);
        checkModel(cuber, gradientSearcher, model, stepsToCheck);
        checkModel(cuber, partialSearcher, model, stepsToCheck);
        checkModel(cuber, branchAndBoundSearcher, model, stepsToCheck);
        checkModel(cuber, equalPricesSearcher, model, stepsToCheck);

    }


    private static void checkModel(Solver cuber, SearchEngine searcher, StateModel modelState, int stepsToCheck) {

        System.out.println(cuber.canReachGoal(searcher, modelState, stepsToCheck));
        System.out.println(searcher.getSearchTree().size());
        System.out.println("----------------------------------------------------");
    }
}
