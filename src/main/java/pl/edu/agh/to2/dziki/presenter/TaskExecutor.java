package pl.edu.agh.to2.dziki.presenter;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;

import java.util.List;

public class TaskExecutor {

    private final Boar boar;
    private final ViewUpdater viewUpdater;

    public TaskExecutor(Boar boar, ViewUpdater viewUpdater) {
        this.boar = boar;
        this.viewUpdater = viewUpdater;
    }

    public void executeTasks(List<Task> tasks) {
        tasks.forEach(task -> task.execute(boar, viewUpdater));
    }
}
