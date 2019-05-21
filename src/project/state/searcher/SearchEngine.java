package project.state.searcher;

import project.state.StateModel;
import project.state.checker.StateChecker;
import project.state.generator.StatesGenerator;

import java.util.Map;

public interface SearchEngine {
    
    Map<byte[], StateModel> getSearchTree();
    
    void reset(StateChecker checker, StatesGenerator generator);
    
    boolean search(StateModel currentState, int maxSteps);
}
