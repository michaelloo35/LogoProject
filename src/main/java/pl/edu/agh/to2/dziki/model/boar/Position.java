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
}
