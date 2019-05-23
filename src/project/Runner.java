package project;

import project.action.Action;
import project.state.StateModel;
import project.state.checker.HanoiStateChecker;
import project.state.checker.StateChecker;
import project.state.generator.StatesGenerator;
import project.state.searcher.SearchEngine;
import project.state.searcher.SearchInDepth;
import project.state.searcher.SearchInWidth;

public class Runner {
    
    public static void main(String[] args) {
        int towersCount = 3;
        int disksCount = 8;
        
        StateChecker checker = new HanoiStateChecker(
            new StateModel(towersCount, disksCount));
        StatesGenerator generator = new StatesGenerator();
        
        StateModel model = new StateModel(towersCount, disksCount);
        model.getCurrentSituation();
        //model.exchangeDisk(0, 1);
        //model.getCurrentSituation();
        
        System.out.println(checker.checkGoal(model));
        model = generator.getNewState(model, new Action(0, 1));
        model = generator.getNewState(model, new Action(0, 2));
        model.getCurrentSituation();
        
        Solver cuber = new Solver(checker, generator);
        SearchEngine widthSearcher = new SearchInWidth();
        SearchEngine depthSearcher = new SearchInDepth();
        
        System.out.println(cuber.canReachGoal(widthSearcher, model, 2));
        System.out.println(widthSearcher.getSearchTree().size());
        System.out.println(cuber.canReachGoal(depthSearcher, model, 2));
        System.out.println(depthSearcher.getSearchTree().size());
    }
    
}
