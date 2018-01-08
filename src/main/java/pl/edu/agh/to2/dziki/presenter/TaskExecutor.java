package pl.edu.agh.to2.dziki.presenter;

import pl.edu.agh.to2.dziki.model.task.Task;

import java.util.List;

public class TaskExecutor {

    public void executeTasks(List<Task> tasks) {
        tasks.forEach(Task::execute);
    }
}
