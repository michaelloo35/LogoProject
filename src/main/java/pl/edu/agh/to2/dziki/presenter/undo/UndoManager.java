package pl.edu.agh.to2.dziki.presenter.undo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.simple.Restart;
import pl.edu.agh.to2.dziki.presenter.task.TaskExecutor;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

import java.util.EmptyStackException;

public class UndoManager {

    private static final Logger log = LogManager.getLogger(UndoManager.class);
    private final TaskExecutor taskExecutor;
    private final ViewUpdater viewUpdater;
    private final Boar boar;

    public UndoManager(TaskExecutor taskExecutor, ViewUpdater viewUpdater, Boar boar) {
        this.taskExecutor = taskExecutor;
        this.viewUpdater = viewUpdater;
        this.boar = boar;
    }

    public void undo() {
        viewUpdater.clearBothLayers();
        taskExecutor.executeUnregisteredTask(new Restart(boar));
        try {
            taskExecutor.popTaskFromHistory();
        } catch (EmptyStackException e) {
            log.debug("There are no tasks in history");
        }
        taskExecutor.executeTasksHistory();
    }

}
