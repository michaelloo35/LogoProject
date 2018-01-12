package pl.edu.agh.to2.dziki.presenter.task;

import pl.edu.agh.to2.dziki.model.task.Task;

import java.util.List;
import java.util.Stack;

public class TaskExecutor {

    private final Stack<Task> tasksHistory;

    public TaskExecutor() {
        tasksHistory = new Stack<>();
    }

    public void executeTasks(List<Task> tasks) {
        tasks.forEach(this::executeTask);
    }

    public void executeTask(Task task) {
        task.execute();
        tasksHistory.add(task);
    }

    public void executeUnregisteredTask(Task task) {
        task.execute();
    }

    public void executeTasksHistory() {
        tasksHistory.forEach(Task::execute);
    }

    public Task popTaskFromHistory() {
        return tasksHistory.pop();
    }
}
