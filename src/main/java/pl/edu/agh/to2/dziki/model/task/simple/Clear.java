package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;

public class Clear implements Task {

    private final Boar boar;

    public Clear(Boar boar) {
        this.boar = boar;
    }

    @Override
    public void execute() {
        boar.clear();
    }
}
