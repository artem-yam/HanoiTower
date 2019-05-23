package project.action;

import java.io.Serializable;
import java.util.Objects;

public class Action implements Serializable {
    private int moveFrom;
    private int moveTo;
    
    public Action(int moveFrom, int moveTo) {
        this.moveFrom = moveFrom;
        this.moveTo = moveTo;
    }
    
    public int getMoveFrom() {
        return moveFrom;
    }
    
    public void setMoveFrom(int moveFrom) {
        this.moveFrom = moveFrom;
    }
    
    public int getMoveTo() {
        return moveTo;
    }
    
    public void setMoveTo(int moveTo) {
        this.moveTo = moveTo;
    }
    
    public Action getReverse() {
        return new Action(moveTo, moveFrom);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Action action = (Action) o;
        return moveFrom == action.moveFrom && moveTo == action.moveTo;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(moveFrom, moveTo);
    }
}
