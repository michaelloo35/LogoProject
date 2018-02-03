package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;

public class Turn implements Task {

    private final double degrees;
    private final Boar boar;

    public Turn(double degrees, Boar boar) {
        this.degrees = degrees;
        this.boar = boar;
    }

    @Override
    public void execute() {
        boar.rotate(degrees);
    }

}
