package pl.edu.agh.to2.dziki.presenter.undo;

import pl.edu.agh.to2.dziki.model.task.Task;

import java.util.Stack;

public interface TaskExecutorObserver {
    void onUndo(Stack<Task> tasksHistory);
}
