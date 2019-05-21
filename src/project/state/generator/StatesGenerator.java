package project.state.generator;

import project.action.Action;
import project.state.StateModel;

import java.util.List;

public interface StatesGenerator {
    
    StateModel getStateClone(StateModel state) throws Exception;
    
    StateModel getNewState(StateModel currentState, Action action);
    
    List<StateModel> getAllNewPossibleStates(StateModel currentState);
    
}
