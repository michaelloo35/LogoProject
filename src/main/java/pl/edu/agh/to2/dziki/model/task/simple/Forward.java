package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Forward implements Task {

    private double distance;

    public Forward(double distance){
        this.distance = distance;
    }

    @Override
    public void execute(Boar model, ViewUpdater updater) {
        model.moveForward(distance);
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
