package pl.edu.agh.to2.dziki.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.dziki.presenter.Position;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class Boar {

    private static final Logger log = LogManager.getLogger(Boar.class);
    private static final String POSITION_MESSAGE = "Boar position set to x: %.2f y: %.2f angle: %.2f degrees";

    //TODO this has to be position of center of the boar not the upper left corner or we have to keep also position of center
    private final Position position;
    private final ViewUpdater viewUpdater;
    private boolean isLift;

    public Boar(ViewUpdater viewUpdater) {
        this.position = new Position();
        this.viewUpdater = viewUpdater;
        this.isLift = false;
    }

    public void initialize() {
        setPosition(0, 0, 0);

        viewUpdater.updateBoarPosition(this);
    }

    private void logPosition() {
        log.info(String.format(POSITION_MESSAGE, position.getX(), position.getY(), position.getRotation()));
    }


    public void rotate(double degrees) {
        position.rotate(degrees);
        viewUpdater.updateBoarPosition(this);
    }

    public void hide() {
        viewUpdater.clearBoarLayer();
    }

    public void show() {
        viewUpdater.updateBoarPosition(this);
    }

    public void moveForward(double distance) {

        double startX = position.getX();
        double startY = position.getY();

        double newX = startX + cos(toRadians(position.getRotation())) * distance;
        double newY = startY + sin(toRadians(position.getRotation())) * distance;

        setPosition(newX, newY, position.getRotation());
        viewUpdater.moveAndDraw(this, startX, startY);
    }

    public void moveBackward(double distance) {

        double startX = position.getX();
        double startY = position.getY();

        double newX = startX - cos(toRadians(position.getRotation())) * distance;
        double newY = startY - sin(toRadians(position.getRotation())) * distance;

        setPosition(newX, newY, position.getRotation());
        viewUpdater.moveAndDraw(this, startX, startY);
    }

    public void setPosition(double x, double y, double rotationDegrees) {
        position.setX(x);
        position.setY(y);
        position.setRotation(rotationDegrees);

        logPosition();
        viewUpdater.updateBoarPosition(this);
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
