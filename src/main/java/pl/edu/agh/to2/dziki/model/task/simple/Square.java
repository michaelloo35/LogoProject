package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;

public class Square implements Task {

    private final double side_length;
    private final Boar boar;

    public Square(double side_length, Boar boar) {
        this.side_length = side_length;
        this.boar = boar;
    }

    @Override
    public void execute() {
        this.boar.fillRectangle(side_length, side_length);
    }
}
