package pl.edu.agh.to2.dziki.model.task;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

public interface Task {
    void execute(Boar boar, ViewUpdater updater);

    boolean isComplex();
}
