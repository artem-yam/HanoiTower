package project.entity;

import java.io.Serializable;
import java.util.Objects;

public class Disk implements Serializable {
    private int weight;
    
    public Disk(int weight) {
        super();
        this.weight = weight;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Disk disk = (Disk) o;
        return weight == disk.weight;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }
}
