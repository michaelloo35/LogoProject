package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Left implements Task {

    private final double xAxisShift;
    private final Boar boar;

    public Left(double xAxisShift, Boar boar) {
        this.xAxisShift = xAxisShift;
        this.boar = boar;
    }

    @Override
    public void execute(ViewUpdater updater) {
        boar.rotate(-90.0);
        boar.moveForward(xAxisShift);
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
