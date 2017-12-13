package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Circle implements Task {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }


    @Override
    public void execute(Boar model, ViewUpdater updater) {

    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
