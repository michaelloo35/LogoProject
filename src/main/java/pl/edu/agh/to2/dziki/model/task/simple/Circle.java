package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Circle implements Task {

    @Override
    public void execute(Boar model, ViewUpdater updater) {

    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
