package pl.edu.agh.to2.dziki.model.task;

import pl.edu.agh.to2.dziki.model.Boar;

public interface ITask<T> {
    void execute(T element);
}
