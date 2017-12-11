package pl.edu.agh.to2.dziki.model.task;

import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.presenter.Position;

import java.util.List;

public class RestartTask implements ITask<Boar> {

    @Override
    public void execute(Boar model, List<String> parameters) {
        model.initialize();
    }
}
