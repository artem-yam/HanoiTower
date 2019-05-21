package project.state.generator;

import project.action.Action;
import project.action.DiskMove;
import project.entity.Disk;
import project.state.StateModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CubeStatesGenerator implements StatesGenerator {
    
    @Override
    public StateModel getStateClone(StateModel state)
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
    
    @Override
    public StateModel getNewState(StateModel currentState, Action action) {
        StateModel newState = null;
        try {
            newState = getStateClone(currentState);
            //newState.setLastAction(null);
            
            Stack<Disk> leftRod;
            Stack<Disk> midRod;
            Stack<Disk> rightRod;
            
            switch ((DiskMove) action) {
                case LEFT_TO_MID:
                    leftRod = newState.getTowers().get(0).getRod();
                    if (!leftRod.isEmpty()) {
                        midRod = newState.getTowers().get(1).getRod();
                        if (midRod.isEmpty() || midRod.peek().getWeight() >
                                                    leftRod.peek()
                                                        .getWeight()) {
                            midRod.push(leftRod.pop());
                            newState.setLastAction(action);
                        }
                    }
                    break;
                case LEFT_TO_RIGHT:
                    leftRod = newState.getTowers().get(0).getRod();
                    if (!leftRod.isEmpty()) {
                        rightRod = newState.getTowers().get(2).getRod();
                        if (rightRod.isEmpty() || rightRod.peek().getWeight() >
                                                      leftRod.peek()
                                                          .getWeight()) {
                            rightRod.push(leftRod.pop());
                            newState.setLastAction(action);
                        }
                    }
                    break;
                case RIGHT_TO_MID:
                    rightRod = newState.getTowers().get(2).getRod();
                    if (!rightRod.isEmpty()) {
                        midRod = newState.getTowers().get(1).getRod();
                        if (midRod.isEmpty() || midRod.peek().getWeight() >
                                                    rightRod.peek()
                                                        .getWeight()) {
                            midRod.push(rightRod.pop());
                            newState.setLastAction(action);
                        }
                    }
                    break;
                case RIGHT_TO_LEFT:
                    rightRod = newState.getTowers().get(2).getRod();
                    if (!rightRod.isEmpty()) {
                        leftRod = newState.getTowers().get(0).getRod();
                        if (leftRod.isEmpty() || leftRod.peek().getWeight() >
                                                     rightRod.peek()
                                                         .getWeight()) {
                            leftRod.push(rightRod.pop());
                            newState.setLastAction(action);
                        }
                    }
                    break;
                case MID_TO_LEFT:
                    midRod = newState.getTowers().get(1).getRod();
                    if (!midRod.isEmpty()) {
                        leftRod = newState.getTowers().get(0).getRod();
                        if (leftRod.isEmpty() || leftRod.peek().getWeight() >
                                                     midRod.peek()
                                                         .getWeight()) {
                            leftRod.push(midRod.pop());
                            newState.setLastAction(action);
                        }
                    }
                    break;
                case MID_TO_RIGHT:
                    midRod = newState.getTowers().get(1).getRod();
                    if (!midRod.isEmpty()) {
                        rightRod = newState.getTowers().get(2).getRod();
                        if (rightRod.isEmpty() || rightRod.peek().getWeight() >
                                                      midRod.peek()
                                                          .getWeight()) {
                            rightRod.push(midRod.pop());
                            newState.setLastAction(action);
                        }
                    }
                    break;
            }
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newState;
    }
    
    @Override
    public List<StateModel> getAllNewPossibleStates(StateModel currentState) {
        List<StateModel> newStates = new ArrayList<>();
        
        Action wasteAction = null;
        if (currentState.getLastAction() != null) {
            wasteAction = currentState.getLastAction().getReverse();
        }
        
        for (DiskMove move : DiskMove.values()) {
            if (wasteAction == null || move != wasteAction) {
                newStates.add(getNewState(currentState, move));
                //newStates.add(getNewState(currentState, rotation));
            }
        }
        
        return newStates;
    }
}
