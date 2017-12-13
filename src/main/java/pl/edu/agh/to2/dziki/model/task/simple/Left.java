package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Left implements Task {

    private double xAxisShift;

    public Left(double xAxisShift){
        this.xAxisShift = xAxisShift;
    }

    @Override
    public void execute(Boar model, ViewUpdater updater) {
        model.rotate(-90.0);
        model.moveForward(xAxisShift);
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
