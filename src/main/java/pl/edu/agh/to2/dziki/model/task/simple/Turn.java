package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.BoarViewUpdater;

public class Turn implements Task {

    @Override
    public void execute(Boar model, BoarViewUpdater updater) {
        model.getPosition().rotate(Integer.parseInt(parameters.get(1)));
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
