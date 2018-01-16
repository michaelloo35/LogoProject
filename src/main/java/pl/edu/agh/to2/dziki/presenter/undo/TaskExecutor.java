package pl.edu.agh.to2.dziki.presenter.undo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.model.task.simple.Restart;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class TaskExecutor {

    private static final Logger log = LogManager.getLogger(TaskExecutor.class);
    private final Stack<Task> tasksHistory;
    private final ViewUpdater viewUpdater;
    private final Boar boar;

    public TaskExecutor(ViewUpdater viewUpdater, Boar boar) {
        this.viewUpdater = viewUpdater;
        this.boar = boar;
        tasksHistory = new Stack<>();
    }

    public void executeTasks(List<Task> tasks) {
        tasks.forEach(this::executeTask);
    }

    public void executeTask(Task task) {
        task.execute();
        tasksHistory.add(task);
    }

    public void undo() {
        viewUpdater.clearBothLayers();
        new Restart(boar).execute();
        try {
            popTaskFromHistory();
            executeTasksHistory();
            log.debug("Undo completed");
        } catch (EmptyStackException e) {
            log.debug("Can't pop stack any task. History is empty");
        }
    }

    private void executeTasksHistory() {
        tasksHistory.forEach(Task::execute);
    }

    private Task popTaskFromHistory() {
        return tasksHistory.pop();
    }
}
