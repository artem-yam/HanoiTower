package project.state.generator;

import project.action.Action;
import project.entity.Disk;
import project.entity.Tower;
import project.state.StateModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StatesGenerator {
    
    private StateModel getStateClone(StateModel state)
        throws IOException, ClassNotFoundException {
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos);
        ous.writeObject(state);
        ous.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(
            baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (StateModel) ois.readObject();
    }
    
    public StateModel getNewState(StateModel currentState, Action action) {
        StateModel newState = null;
        try {
            newState = getStateClone(currentState);
            
            Stack<Disk> oldRod = newState.getTowers().get(action.getMoveFrom())
                                     .getRod();
            Stack<Disk> newRod = newState.getTowers().get(action.getMoveTo())
                                     .getRod();
            
            newRod.push(oldRod.pop());
            newState.setLastAction(action);
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newState;
    }
    
    public List<StateModel> getAllNewPossibleStates(StateModel currentState) {
        List<StateModel> newStates = new ArrayList<>();
        
        for (Tower fromTower : currentState.getTowers()) {
            if (!fromTower.getRod().isEmpty()) {
                
                for (Tower toTower : currentState.getTowers()) {
                    if (!fromTower.equals(toTower) &&
                            toTower.getRod().isEmpty() ||
                            toTower.getRod().peek().getWeight() >
                                fromTower.getRod().peek().getWeight()) {
                        
                        Action action = new Action(
                            currentState.getTowers().indexOf(fromTower),
                            currentState.getTowers().indexOf(toTower));
                        
                        if (currentState.getLastAction() == null ||
                                !action.equals(currentState.getLastAction()
                                                   .getReverse())) {
                            newStates.add(getNewState(currentState, action));
                        }
                        
                    }
                }
            }
        }
        
        return newStates;
    }
}
