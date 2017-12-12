package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.BoarViewUpdater;

public class Forward implements Task {

    @Override
    public void execute(Boar model, BoarViewUpdater updater) {
        double actualYPosition = model
                .getPosition()
                .getY();

        model.getPosition().setY(actualYPosition +
                Integer.parseInt(parameters.get(1)));
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
