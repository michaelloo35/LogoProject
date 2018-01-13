package pl.edu.agh.to2.dziki.model.task.simple;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;

public class Lower implements Task {

    private final Boar boar;

    public Lower(Boar boar) {
        this.boar = boar;
    }

    @Override
    public void execute() {
        boar.lower();
    }

}
