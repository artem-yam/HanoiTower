package project.action;

public enum DiskMove implements Action {
    LEFT_TO_MID, LEFT_TO_RIGHT, RIGHT_TO_MID, RIGHT_TO_LEFT, MID_TO_LEFT,
    MID_TO_RIGHT;
    
    @Override
    public Action getReverse() {
        Action reverse = null;
        switch (this) {
            case LEFT_TO_MID:
                reverse = MID_TO_LEFT;
                break;
            case LEFT_TO_RIGHT:
                reverse = RIGHT_TO_LEFT;
                break;
            case RIGHT_TO_MID:
                reverse = MID_TO_RIGHT;
                break;
            case RIGHT_TO_LEFT:
                reverse = LEFT_TO_RIGHT;
                break;
            case MID_TO_LEFT:
                reverse = LEFT_TO_MID;
                break;
            case MID_TO_RIGHT:
                reverse = RIGHT_TO_MID;
                break;
        }
        return reverse;
    }
}
