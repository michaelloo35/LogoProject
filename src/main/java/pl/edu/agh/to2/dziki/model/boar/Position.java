package pl.edu.agh.to2.dziki.model.boar;

public class Position {
    private double x;
    private double y;
    private double rotation;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation % 360;
    }

    public void rotate(double angle) {
        this.rotation = (this.rotation + angle) % 360;
    }
}
