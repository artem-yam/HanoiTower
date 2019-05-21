package project.state;

import project.action.Action;
import project.entity.Disk;
import project.entity.Tower;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StateModel implements Serializable {
    private static final int TOWERS_COUNT = 3;
    
    private List<Tower> towers;
    private Action lastAction = null;
    
    public StateModel(int disksCount) {
        createTowers();
        
        fillTower(disksCount, 0);
    }
    
    private StateModel() {
    }
    
    public static StateModel getWinState(int disksCount) {
        StateModel model = new StateModel();
        
        model.createTowers();
        model.fillTower(disksCount, TOWERS_COUNT - 1);
        
        return model;
    }
    
    private void createTowers() {
        towers = new ArrayList<>(TOWERS_COUNT);
        for (int i = 0; i < TOWERS_COUNT; i++) {
            towers.add(new Tower());
        }
    }
    
    private void fillTower(int disksCount, int towerNumber) {
        if (towers.size() > towerNumber) {
            for (int i = disksCount; i > 0; i--) {
                Disk disk = new Disk(i);
                towers.get(towerNumber).addDisk(disk);
            }
        }
    }
    
    public void exchangeDisk(int indexFrom, int indexTo) {
        Tower towerFrom = towers.get(indexFrom);
        Tower towerTo = towers.get(indexTo);
        
        if ((towerTo.isRodEmpty()) ||
                (towerFrom.getWeightLastDisk() < towerTo.getWeightLastDisk())) {
            Disk disk = towerFrom.deleteDisk();
            towerTo.addDisk(disk);
        }
    }
    
    public List<Tower> getTowers() {
        return towers;
    }
    
    public void getCurrentSituation() {
        //todo доделать вывод ситуации
        /*StringBuffer sb = new StringBuffer();
        String formatString = "%3s";
        
        int longestTower = 0;
        
        for (Tower tower : towers) {
            if (tower.getRod().size() > longestTower) {
                longestTower = tower.getRod().size();
            }
        }
        
        for (int i = 0; i < longestTower; i++) {
            sb.append(String.format(formatString, " "));
            
            for (Tower tower : towers) {
                if (tower.getRod().size() >= longestTower - i) {
                
                }
            }
            
            sb.append(String.format(formatString, "|"));
        }*/
        
        for (Tower tower : towers) {
            if (!(tower.isRodEmpty())) {
                for (int j = tower.getRod().size(); j > 0; j--) {
                    Disk disk = tower.getRod().get(j - 1);
                    System.out.println(disk.getWeight());
                }
            } else {
                System.out.println("Rod is empty");
            }
            System.out.println("\n");
        }
        System.out.println("--------------------------------");
    }
    
    public Action getLastAction() {
        return lastAction;
    }
    
    public void setLastAction(Action lastAction) {
        this.lastAction = lastAction;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StateModel that = (StateModel) o;
        return Objects.equals(towers, that.towers);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(towers);
    }
}
