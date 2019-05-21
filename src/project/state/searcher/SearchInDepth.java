package project.state.searcher;

import project.action.Action;
import project.action.DiskMove;
import project.state.StateModel;

import java.util.HashSet;

public class SearchInDepth extends AbstractSearch {
    
    protected HashSet<StateModel> visitedStates;
    
    @Override
    public boolean search(StateModel currentState, int maxSteps) {
        visitedStates = new HashSet<>();
        
        searchTree.put(new byte[]{(byte) currentStep}, currentState);
        visitedStates.add(currentState);
        
        return recursiveSearch(currentState, maxSteps);
    }
    
    private boolean recursiveSearch(StateModel currentState, int maxSteps) {
        for (Action rotation : DiskMove.values()) {
            
            if (++currentStep <= maxSteps) {
                StateModel newState = generator.getNewState(currentState,
                    rotation);
                
                checkStateAndChildren(currentState, newState, maxSteps);
            } else {
                currentStep--;
                return isFinished;
            }
            
        }
        
        return isFinished;
    }
    
    protected void checkStateAndChildren(StateModel parentState,
        StateModel state, int maxSteps) {
        if (!visitedStates.contains(state)) {
            
            visitedStates.add(state);
            fillTree(searchTree, parentState, state);
            
            isFinished = checker.checkGoal(state) || recursiveSearch(state,
                maxSteps);
            
            currentStep--;
            
        }
    }
}
