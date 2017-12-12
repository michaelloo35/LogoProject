package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Backward implements Task {

    private double distance;

    public Backward(double distance){
        this.distance = distance;
    }

    @Override
    public void execute(Boar model, ViewUpdater updater) {
        model.moveBackward(distance);
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
