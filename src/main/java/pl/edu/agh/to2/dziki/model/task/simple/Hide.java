package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;

public class Hide implements Task {

    private final Boar boar;

    public Hide(Boar boar) {
        this.boar = boar;
    }

    @Override
    public void execute() {
        boar.hide();
    }

}
