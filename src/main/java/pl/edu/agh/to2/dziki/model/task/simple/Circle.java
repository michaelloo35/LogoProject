package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Circle implements Task {

    private double radius;
    private final Boar boar;

    public Circle(double radius, Boar boar) {
        this.radius = radius;
        this.boar = boar;
    }


    @Override
    public void execute(ViewUpdater updater) {

    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
