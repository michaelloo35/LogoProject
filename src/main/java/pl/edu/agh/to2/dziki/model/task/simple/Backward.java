package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Backward implements Task {

    private final double distance;
    private final Boar boar;

    public Backward(double distance, Boar boar) {
        this.distance = distance;
        this.boar = boar;
    }

    @Override
    public void execute(ViewUpdater updater) {
        boar.moveBackward(distance);
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
