package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Turn implements Task {

    private double degrees;

    public Turn(double degrees) {
        this.degrees = degrees;
    }


    @Override
    public void execute(Boar model, ViewUpdater updater) {
        model.rotate(degrees);
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
