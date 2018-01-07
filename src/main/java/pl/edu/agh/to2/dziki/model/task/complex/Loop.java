package pl.edu.agh.to2.dziki.model.task.complex;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

import java.util.List;

public class Loop implements Task {

    private final Boar boar;
    private final int numberOfIterations;
    private final List<Task> nestedTasks;

    public Loop(Boar boar, int numberOfIterations, List<Task> nestedTasks) {
        this.boar = boar;
        this.numberOfIterations = numberOfIterations;
        this.nestedTasks = nestedTasks;
    }

    @Override
    public void execute(ViewUpdater updater) {
        for (int i = 0; i < numberOfIterations; i++) {
            nestedTasks.forEach(t -> t.execute(updater));
        }
    }

    @Override
    public boolean isComplex() {
        return true;
    }

}
