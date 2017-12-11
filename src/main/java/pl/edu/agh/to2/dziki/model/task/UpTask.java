package pl.edu.agh.to2.dziki.model.task;

import pl.edu.agh.to2.dziki.model.Boar;

import java.util.List;

public class UpTask implements ITask<Boar> {

    @Override
    public void execute(Boar model, List<String> parameters) {
        double actualYPosition = model
                .getPosition()
                .getY();

        model.getPosition().setY( actualYPosition +
                Integer.parseInt(parameters.get(1)));
    }
}
