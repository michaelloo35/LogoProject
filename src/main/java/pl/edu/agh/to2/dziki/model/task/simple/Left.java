package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.BoarViewUpdater;

public class Left implements Task {

    private double xAxisShift;

    public Left(double xAxisShift){
        this.xAxisShift = xAxisShift;
    }

    @Override
    public void execute(Boar model, BoarViewUpdater updater) {
        double actualXPosition = model
                .getPosition()
                .getX();

        model.getPosition().setY(actualXPosition -
                this.xAxisShift);
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
