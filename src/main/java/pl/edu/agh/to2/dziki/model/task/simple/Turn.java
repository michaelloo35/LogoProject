package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.BoarViewUpdater;

public class Turn implements Task {

    private double degree;

    public Turn(double degree) {
        this.degree = degree;
    }


    @Override
    public void execute(Boar model, BoarViewUpdater updater) {
        model.getPosition().rotate(this.degree);
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
