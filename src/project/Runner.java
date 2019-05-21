package project;

import project.action.DiskMove;
import project.state.StateModel;
import project.state.checker.CubeStateChecker;
import project.state.checker.StateChecker;
import project.state.generator.CubeStatesGenerator;
import project.state.generator.StatesGenerator;
import project.state.searcher.SearchEngine;
import project.state.searcher.SearchInWidth;

public class Runner {
    
    public static void main(String[] args) {
        int diskNumber = 8;
        
        StateChecker checker = new CubeStateChecker(new StateModel(diskNumber));
        StatesGenerator generator = new CubeStatesGenerator();
        
        StateModel model = new StateModel(diskNumber);
        model.getCurrentSituation();
        //model.exchangeDisk(0, 1);
        //model.getCurrentSituation();
        
        System.out.println(checker.checkGoal(model));
        model = generator.getNewState(model, DiskMove.LEFT_TO_MID);
        model = generator.getNewState(model, DiskMove.LEFT_TO_RIGHT);
        model.getCurrentSituation();
        
        Cuber cuber = new Cuber(checker, generator);
        SearchEngine widthSearcher = new SearchInWidth();
        
        System.out.println(cuber.canReachGoal(widthSearcher, model, 2));
        System.out.println(widthSearcher.getSearchTree().size());
    }
    
}
