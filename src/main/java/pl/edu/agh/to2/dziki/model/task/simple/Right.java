package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Right implements Task {

    private double xAxisShift;

    public Right(double xAxisShift){
        this.xAxisShift = xAxisShift;
    }

    @Override
    public void execute(Boar model, ViewUpdater updater) {
        model.rotate(90.0);
        model.moveForward(xAxisShift);
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
