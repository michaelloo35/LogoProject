package pl.edu.agh.to2.dziki.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.dziki.presenter.BoarViewUpdater;
import pl.edu.agh.to2.dziki.presenter.Position;

public class Boar {

    private static final Logger log = LogManager.getLogger(Boar.class);
    private static final String POSITION_MESSAGE = "Boar position set to x:%.2f y:%.2f %n";

    //TODO this has to be position of center of the boar not the upper left corner or we have to keep also position of center
    private final Position position;
    private final BoarViewUpdater boarViewUpdater;
    private boolean isLift;

    public Boar(BoarViewUpdater boarViewUpdater) {
        this.position = new Position();
        this.boarViewUpdater = boarViewUpdater;
        this.isLift = false;
    }

    public void initialize() {
        this.position.setX(0);
        this.position.setY(0);
        log.info(String.format(POSITION_MESSAGE, 0.0, 0.0));

        boarViewUpdater.initialize(this);
    }

    public void initialize(double x, double y) {
        this.position.setX(x);
        this.position.setY(y);
        log.info(String.format(POSITION_MESSAGE, x, y));

        boarViewUpdater.initialize(this);
    }

    public void rotate(double degrees) {
        position.rotate(degrees);
        boarViewUpdater.updateRotation(this);
    }

    public void setPosition(double x, double y, double rotationDegrees) {
        position.setX(x);
        position.setY(y);
        position.setRotation(rotationDegrees);

        boarViewUpdater.initialize(this);
        boarViewUpdater.updateRotation(this);
    }

    public void hide() {
        boarViewUpdater.clearCanvas();
    }

    public void show() {
        boarViewUpdater.initialize(this);
    }

    public void moveForward(double distance) {
        return;
    }

    public void moveBackward(double distance) {
        return;
    }

    public Position getPosition() {
        return position;
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

}
