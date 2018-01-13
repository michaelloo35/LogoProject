package pl.edu.agh.to2.dziki.model.boar;

public class BoarActionData {
    private final Position previousPosition;
    private final Position newPosition;
    private final boolean isLift;
    private final boolean isHidden;


    public BoarActionData(Position previousPosition, Position newPosition, boolean isLift, boolean isHidden) {
        this.previousPosition = previousPosition;
        this.newPosition = newPosition;
        this.isLift = isLift;
        this.isHidden = isHidden;
    }

    public boolean isLift() {
        return isLift;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }

    public Position getNewPosition() {
        return newPosition;
    }
}
