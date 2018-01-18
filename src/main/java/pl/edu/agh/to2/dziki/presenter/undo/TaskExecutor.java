package pl.edu.agh.to2.dziki.presenter.undo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.utils.UnmodifiableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TaskExecutor implements ObservableTaskExecutor {

    private static final Logger log = LogManager.getLogger(TaskExecutor.class);
    private final int setupTasksNumber;
    private final Stack<Task> tasksHistory;
    private final List<TaskExecutorObserver> observers;

    public TaskExecutor(int setupTasksNumber) {
        this.setupTasksNumber = setupTasksNumber;
        this.tasksHistory = new Stack<>();
        this.observers = new ArrayList<>();
    }

    public void executeTasks(List<Task> tasks) {
        tasks.forEach(this::executeTask);
    }

    public void executeTask(Task task) {
        task.execute();
        tasksHistory.add(task);
    }

    public void undo() {
        // pops the last task from history if we haven't reached setup tasks
        if (tasksHistory.size() > setupTasksNumber)
            tasksHistory.pop();

        // notifies observers
        observers.forEach(o -> o.onUndo(new UnmodifiableList<>(tasksHistory)));
    }

    @Override
    public void subscribe(TaskExecutorObserver observer) {
        this.observers.add(observer);
    }
}
