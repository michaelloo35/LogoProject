package pl.edu.agh.to2.dziki.presenter;

import pl.edu.agh.to2.dziki.model.task.Task;

import java.util.List;

public class TaskExecutor {

    private final ViewUpdater viewUpdater;

    public TaskExecutor(ViewUpdater viewUpdater) {
        this.viewUpdater = viewUpdater;
    }

    public void executeTasks(List<Task> tasks) {
        tasks.forEach(task -> task.execute());
    }
}
