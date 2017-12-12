package pl.edu.agh.to2.dziki.model.task;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.presenter.BoarViewUpdater;

public interface Task {
    void execute(Boar boar, BoarViewUpdater updater);

    boolean isComplex();
}
