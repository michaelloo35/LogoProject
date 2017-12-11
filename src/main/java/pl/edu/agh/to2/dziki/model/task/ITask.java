package pl.edu.agh.to2.dziki.model.task;

import java.util.List;

public interface ITask<T> {
    void execute(T model, List<String> parameters);
}
