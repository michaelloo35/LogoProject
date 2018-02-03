package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;

public class Forward implements Task {

    private final double distance;
    private final Boar boar;

    public Forward(double distance, Boar boar) {
        this.distance = distance;
        this.boar = boar;
    }

    @Override
    public void execute() {
        boar.moveForward(distance);
    }

}
