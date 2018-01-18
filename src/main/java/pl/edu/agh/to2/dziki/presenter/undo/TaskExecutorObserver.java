package pl.edu.agh.to2.dziki.presenter.undo;

import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.utils.UnmodifiableList;

public interface TaskExecutorObserver {
    void onUndo(UnmodifiableList<Task> tasksHistory);
}
