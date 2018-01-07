package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Restart implements Task {

    private final Boar boar;

    public Restart(Boar boar) {
        this.boar = boar;
    }

    @Override
    public void execute(ViewUpdater updater) {
        boar.initialize();
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
