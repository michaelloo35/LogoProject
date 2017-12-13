package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Hide implements Task {

    @Override
    public void execute(Boar model, ViewUpdater updater) {
        model.hide();
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
