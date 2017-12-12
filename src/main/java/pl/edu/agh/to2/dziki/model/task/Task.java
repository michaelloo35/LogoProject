package pl.edu.agh.to2.dziki.model.task;

import java.util.List;

public interface Task<T> {
    void execute(T model, List<String> parameters);
}
