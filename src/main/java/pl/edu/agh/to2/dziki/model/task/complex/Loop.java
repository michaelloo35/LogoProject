package pl.edu.agh.to2.dziki.model.task.complex;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.model.complexTask.ComplexTask;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.BoarViewUpdater;

import java.util.List;

public class Loop implements Task{

    @Override
    public void execute(Boar boar, BoarViewUpdater updater) {

    }

    @Override
    public boolean isComplex() {
        return false;
    }

}
