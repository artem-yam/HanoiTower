package project.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Stack;

public class Tower implements Serializable {
    private Stack<Disk> rod;
    
    public Tower() {
        this.rod = new Stack<>();
    }
    
    public void addDisk(Disk disk) {
        rod.push(disk);
        //rod.add(disk);
    }
    
    /*public Disk deleteDisk() {
        int currentDisk = rod.size() - 1;
        Disk disk = new Disk(rod.get(currentDisk).getWeight());
        rod.remove(currentDisk);
        return disk;
    }
    
    public int getWeightLastDisk() {
        int indexLastDisk = rod.size() - 1;
        Disk disk = rod.get(indexLastDisk);
        return disk.getWeight();
    }*/
    
    public boolean isRodEmpty() {
        if (rod.isEmpty()) {
            return true;
        }
        return false;
    }
    
    public Stack<Disk> getRod() {
        return rod;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tower tower = (Tower) o;
        return Objects.equals(rod, tower.rod);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(rod);
    }
}
