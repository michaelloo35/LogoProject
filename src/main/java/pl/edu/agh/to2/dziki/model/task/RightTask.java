package pl.edu.agh.to2.dziki.model.task;

import pl.edu.agh.to2.dziki.model.Boar;

import java.util.List;

public class RightTask implements Task<Boar> {

    @Override
    public void execute(Boar model, List<String> parameters) {
        double actualXPosition = model
                .getPosition()
                .getX();

        model.getPosition().setY( actualXPosition +
                Integer.parseInt(parameters.get(1)));
    }
}
