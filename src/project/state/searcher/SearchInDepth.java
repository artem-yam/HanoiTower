package project.state.searcher;

import project.state.StateModel;

import java.util.HashSet;

public class SearchInDepth extends AbstractSearch {
    
    protected HashSet<StateModel> visitedStates;
    private StateModel startState;
    
    @Override
    public boolean search(StateModel currentState, int maxSteps) {
        visitedStates = new HashSet<>();
        startState = currentState;
        
        searchTree.put(new byte[]{(byte) currentStep}, currentState);
        
        return recursiveSearch(currentState, maxSteps);
    }
    
    private boolean recursiveSearch(StateModel currentState, int maxSteps) {
        
        for (StateModel newState : generator.getAllNewPossibleStates(
            currentState)) {
            
            if (currentStep == 0) {
                visitedStates = new HashSet<>();
                visitedStates.add(startState);
            }
            
            if (++currentStep <= maxSteps) {
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
