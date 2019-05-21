package project.state.searcher;

import project.state.StateModel;
import project.state.checker.StateChecker;
import project.state.generator.StatesGenerator;
import project.utils.ByteArrayComparator;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractSearch implements SearchEngine {
    
    protected boolean isFinished;
    protected int currentStep;
    protected StateChecker checker;
    protected StatesGenerator generator;
    
    protected Map<byte[], StateModel> searchTree;
    
    @Override
    public Map<byte[], StateModel> getSearchTree() {
        return searchTree;
    }
    
    @Override
    public void reset(StateChecker checker, StatesGenerator generator) {
        this.checker = checker;
        this.generator = generator;
        
        isFinished = false;
        currentStep = 0;
        
        searchTree = new TreeMap(new ByteArrayComparator());
    }
    
    protected void fillTree(Map<byte[], StateModel> treeMap,
        StateModel parentNode, List<StateModel> childNodes) {
        byte[] bytes;
        byte[] stateKey = getParentKey(treeMap, parentNode);
        
        for (StateModel someState : childNodes) {
            bytes = new byte[stateKey.length + 1];
            for (int i = 0; i < stateKey.length; i++) {
                bytes[i] = stateKey[i];
            }
            bytes[bytes.length - 1] = (byte) childNodes.indexOf(someState);
            
            treeMap.put(bytes, someState);
        }
    }
    
    protected void fillTree(Map<byte[], StateModel> treeMap,
        StateModel parentNode, StateModel childNode) {
        
        byte[] stateKey = getParentKey(treeMap, parentNode);
        
        byte[] bytes = new byte[stateKey.length + 1];
        for (int i = 0; i < stateKey.length; i++) {
            bytes[i] = stateKey[i];
        }
        
        byte[] tempKey = bytes.clone();
        tempKey[tempKey.length - 2] = (byte) (stateKey[bytes.length - 2] + 1);
        
        int childNodeLevelKeysCount = 0;
        
        for (byte[] key : treeMap.keySet()) {
            
            Comparator comp = new ByteArrayComparator();
            
            if (comp.compare(key, bytes) >= 0 && comp.compare(key, tempKey) <
                                                     0) {
                childNodeLevelKeysCount++;
            }
        }
        
        bytes[bytes.length - 1] = (byte) (childNodeLevelKeysCount);
        treeMap.put(bytes, childNode);
        
    }
    
    private byte[] getParentKey(Map<byte[], StateModel> treeMap,
        StateModel parentNode) {
        byte[] stateKey = new byte[0];
        
        for (byte[] key : treeMap.keySet()) {
            if (treeMap.get(key).equals(parentNode)) {
                stateKey = key;
                break;
            }
        }
        return stateKey;
    }
    
    protected void checkStates(List<StateModel> states) {
        for (StateModel state : states) {
            if (isFinished = checker.checkGoal(state)) {
                break;
            }
        }
    }
    
}
