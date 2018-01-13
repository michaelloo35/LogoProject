package pl.edu.agh.to2.dziki.model.boar;

import com.google.common.annotations.VisibleForTesting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

/**
 * Boar model class that emits on state change events
 * when Boar's current position is equal to his previous position no line shall be drawn
 * this means that the boar 'teleported' instead of moving
 */
public class Boar implements ObservableBoar {

    private static final Logger log = LogManager.getLogger(Boar.class);
    private static final String POSITION_MESSAGE = "Boar position set to x: %.2f y: %.2f angle: %.2f degrees";

    private final Position position;
    private List<BoarObserver> observers;
    private boolean isLift;
    private boolean isHidden;

    public Boar() {
        this.position = new Position();
        this.observers = new ArrayList<>();
        this.isLift = false;
        this.isHidden = false;
    }

    public void initialize() {
        setCurrentPosition(0, 0, 0);
        isLift = false;
        isHidden = false;

        observers.forEach(o -> o.onInitialize(
                new BoarActionData(
                        new Position(position),
                        new Position(position),
                        isLift, isHidden)));
    }

    private void logPosition() {
        log.debug(String.format(POSITION_MESSAGE, position.getX(), position.getY(), position.getRotation()));
    }


    public void rotate(double degrees) {

        Position previousPosition = new Position(position);
        position.rotate(degrees);
        Position newPosition = new Position(position);

        observers.forEach(o -> o.onRotate(
                new BoarActionData(previousPosition, newPosition, isLift, isHidden)));
    }

    public void hide() {
        isHidden = true;
        observers.forEach(BoarObserver::onHide);
    }

    public void show() {
        Position previousPosition = new Position(position);
        isHidden = false;
        Position newPosition = new Position(position);
        observers.forEach(o -> o.onShow(new BoarActionData(previousPosition, newPosition, isLift, isHidden)));
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void moveForward(double distance) {

        Position previousPosition = new Position(position);
        double startX = position.getX();
        double startY = position.getY();

        double newX = startX + cos(toRadians(position.getRotation())) * distance;
        double newY = startY + sin(toRadians(position.getRotation())) * distance;

        setCurrentPosition(newX, newY, position.getRotation());
        Position newPosition = new Position(position);

        observers.forEach(o -> o.onMove(
                new BoarActionData(previousPosition, newPosition, isLift, isHidden)));

    }

    public void moveBackward(double distance) {

        Position previousPosition = new Position(position);
        double startX = position.getX();
        double startY = position.getY();

        double newX = startX - cos(toRadians(position.getRotation())) * distance;
        double newY = startY - sin(toRadians(position.getRotation())) * distance;

        setCurrentPosition(newX, newY, position.getRotation());
        Position newPosition = new Position(position);

        observers.forEach(o -> o.onMove(
                new BoarActionData(previousPosition, newPosition, isLift, isHidden)));

    }

    public void lift() {
        isLift = true;
    }

    public void lower() {
        isLift = false;
    }

    public boolean isLift() {
        return isLift;
    }

    @VisibleForTesting
    void setCurrentPosition(double x, double y, double rotationAngle) {
        position.setX(x);
        position.setY(y);
        position.setRotation(rotationAngle);

        logPosition();
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void subscribe(BoarObserver observer) {
        this.observers.add(observer);
    }
}
