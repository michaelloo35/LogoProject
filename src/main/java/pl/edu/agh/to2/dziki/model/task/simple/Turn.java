package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Turn implements Task {

    private double degree;

    public Turn(double degree) {
        this.degree = degree;
    }


    @Override
    public void execute(Boar model, ViewUpdater updater) {
        model.rotate(degree);
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
