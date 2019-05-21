package project;

import project.state.StateModel;
import project.state.checker.StateChecker;
import project.state.generator.StatesGenerator;
import project.state.searcher.SearchEngine;

public class Cuber {
    
    private StateChecker checker;
    private StatesGenerator generator;
    
    public Cuber(StateChecker checker, StatesGenerator generator) {
        this.checker = checker;
        this.generator = generator;
    }
    
    private void reset(SearchEngine searcher) {
        searcher.reset(checker, generator);
    }
    
    public boolean canReachGoal(SearchEngine searcher, StateModel currentState,
        int steps) {
        reset(searcher);
        boolean result;
        
        currentState.setLastAction(null);
        
        if (!(result = checker.checkGoal(currentState))) {
            result = searcher.search(currentState, steps);
        }
        
        return result;
    }
}
