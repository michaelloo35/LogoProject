package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Forward implements Task {

    private double yAxisShift;

    public Forward(double yAxisShift){
        this.yAxisShift = yAxisShift;
    }

    @Override
    public void execute(Boar model, ViewUpdater updater) {
        double actualYPosition = model
                .getPosition()
                .getY();

        model.getPosition().setY(actualYPosition +
                this.yAxisShift);
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
