package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;

public class Rectangle implements Task {
    private final double width;
    private final double height;
    private final Boar boar;

    public Rectangle(double width, double height, Boar boar) {
        this.width = width;
        this.height = height;
        this.boar = boar;
    }

    @Override
    public void execute() {
        this.boar.fillRectangle(width, height);
    }
}
