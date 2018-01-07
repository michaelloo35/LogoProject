package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Show implements Task {

    private final Boar boar;

    public Show(Boar boar) {
        this.boar = boar;
    }

    @Override
    public void execute(ViewUpdater updater) {
        boar.show();
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
