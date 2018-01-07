package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public class Lift implements Task {

    private final Boar boar;

    public Lift(Boar boar) {
        this.boar = boar;
    }

    @Override
    public void execute(ViewUpdater updater) {
        boar.lift();
    }

    @Override
    public boolean isComplex() {
        return false;
    }
}
