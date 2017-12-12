package pl.edu.agh.to2.dziki.model.task.complex;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

import java.util.List;

public class Loop implements Task {

    private final int numberOfIterations;
    private final List<Task> nestedTasks;

    public Loop(int numberOfIterations, List<Task> nestedTasks) {
        this.numberOfIterations = numberOfIterations;
        this.nestedTasks = nestedTasks;
    }

    @Override
    public void execute(Boar boar, ViewUpdater updater) {
        for (int i = 0; i < numberOfIterations; i++) {
            nestedTasks.forEach(t -> t.execute(boar, updater));
        }
    }

    @Override
    public boolean isComplex() {
        return true;
    }

}
