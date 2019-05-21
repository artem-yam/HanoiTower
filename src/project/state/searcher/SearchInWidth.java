package project.state.searcher;

import project.state.StateModel;

import java.util.ArrayList;
import java.util.List;

public class SearchInWidth extends AbstractSearch {
    
    @Override
    public boolean search(StateModel currentState, int maxSteps) {
        
        List<StateModel> newStates = new ArrayList<>();
        newStates.add(currentState);
        
        searchTree.put(new byte[]{(byte) currentStep}, currentState);
        
        while (!isFinished && currentStep < maxSteps) {
            newStates = generateNextStep(newStates);
        }
        
        return isFinished;
    }
    
    protected List<StateModel> generateNextStep(List<StateModel> states) {
        currentStep++;
        
        List<StateModel> allStates = new ArrayList<>();
        
        for (StateModel state : states) {
            
            List<StateModel> nextStates = generator.getAllNewPossibleStates(
                state);
            
            fillTree(searchTree, state, nextStates);
            
            allStates.addAll(nextStates);
        }
        
        checkStates(allStates);
        
        return allStates;
    }
    
}
