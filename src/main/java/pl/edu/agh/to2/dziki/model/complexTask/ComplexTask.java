package pl.edu.agh.to2.dziki.model.complexTask;

import pl.edu.agh.to2.dziki.model.task.Task;

import java.util.List;

public interface ComplexTask {
    List<Task> disassembleComplexTask(List<String> complexTask);
}
