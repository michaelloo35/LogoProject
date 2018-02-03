package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;

public class Oval implements Task {
    private final double width;
    private final double height;
    private final Boar boar;

    public Oval(double width, double height, Boar boar) {
        this.width = width;
        this.height = height;
        this.boar = boar;
    }

    @Override
    public void execute() {
        this.boar.fillOval(width, height);
    }
}
