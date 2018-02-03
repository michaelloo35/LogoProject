package pl.edu.agh.to2.dziki.model.boar;

public class Position {
    private double x;
    private double y;
    private double rotation;

    public Position() {
        this.x = 0;
        this.y = 0;
        this.rotation = 0;
    }

    public Position(Position other) {
        this.x = other.getX();
        this.y = other.getY();
        this.rotation = other.getRotation();
    }

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

    public void setRotation(double angle) {
        this.rotation = 0;
        rotate(angle);
    }

    public void rotate(double angle) {
        this.rotation = (this.rotation + angle) % 360;

        // normalization
        if (this.rotation < 0)
            this.rotation += 360;
    }

    public boolean hasSameCoordinatesAs(Position p) {
        return x == p.getX() && y == p.getY();
    }
}
