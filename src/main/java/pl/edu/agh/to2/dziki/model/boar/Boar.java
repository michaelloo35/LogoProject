package pl.edu.agh.to2.dziki.model.boar;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
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
public class Boar implements ObservableSource<Boar> {

    private static final Logger log = LogManager.getLogger(Boar.class);
    private static final String POSITION_MESSAGE = "Boar currentPosition set to x: %.2f y: %.2f angle: %.2f degrees";

    private final Position previousPosition;
    private final Position currentPosition;
    private List<Observer<Boar>> observers;
    private boolean isLift;
    private boolean isHidden;

    public Boar() {
        previousPosition = new Position();
        this.currentPosition = new Position();
        this.observers = new ArrayList<>();
        this.isLift = false;
        this.isHidden = false;
    }

    public void initialize() {
        setPreviousPosition(0, 0, 0);
        setCurrentPosition(0, 0, 0);
        isLift = false;
        isHidden = false;

        observers.forEach(o -> o.onNext(this));
    }

    private void logPosition() {
        log.debug(String.format(POSITION_MESSAGE, currentPosition.getX(), currentPosition.getY(), currentPosition.getRotation()));
    }


    public void rotate(double degrees) {
        setPreviousPosition(currentPosition.getX(), currentPosition.getY(), currentPosition.getRotation());

        currentPosition.rotate(degrees);
        observers.forEach(o -> o.onNext(this));
    }

    public void hide() {
        isHidden = true;
        observers.forEach(o -> o.onNext(this));
    }

    public void show() {
        isHidden = false;
        observers.forEach(o -> o.onNext(this));
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void moveForward(double distance) {

        setPreviousPosition(currentPosition.getX(), currentPosition.getY(), currentPosition.getRotation());

        double newX = previousPosition.getX() + cos(toRadians(previousPosition.getRotation())) * distance;
        double newY = previousPosition.getY() + sin(toRadians(previousPosition.getRotation())) * distance;

        setCurrentPosition(newX, newY, currentPosition.getRotation());
        observers.forEach(o -> o.onNext(this));

    }

    public void moveBackward(double distance) {

        setPreviousPosition(currentPosition.getX(), currentPosition.getY(), currentPosition.getRotation());

        double newX = previousPosition.getX() - cos(toRadians(previousPosition.getRotation())) * distance;
        double newY = previousPosition.getY() - sin(toRadians(previousPosition.getRotation())) * distance;

        setCurrentPosition(newX, newY, currentPosition.getRotation());
        observers.forEach(o -> o.onNext(this));

    }

    public void setPreviousPosition(double x, double y, double rotationAngle) {
        previousPosition.setX(x);
        previousPosition.setY(y);
        previousPosition.setRotation(rotationAngle);
    }

    public void setCurrentPosition(double x, double y, double rotationAngle) {
        currentPosition.setX(x);
        currentPosition.setY(y);
        currentPosition.setRotation(rotationAngle);

        logPosition();
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     * If boar moves he remembers his previous currentPosition
     */

    public Position getPreviousPosition() {
        return previousPosition;
    }

    public boolean isLift() {
        return isLift;
    }

    public void lift() {
        isLift = true;
    }

    public void lower() {
        isLift = false;
    }

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }
}
