package pl.edu.agh.to2.dziki.presenter.parser;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

// Alias commands by convention should be written in same line. When adding an alias case condition in TaskCreator shall be updated
public enum Command {
    FORWARD(1),
    BACKWARD(1),
    RIGHT(1),
    LEFT(1),
    TURN(1), ROTATE(1),
    RESTART(0), START(0),
    HIDE(0),
    SHOW(0),
    CLEAR(0),
    LIFT(0),
    LOWER(0),
    LOOP(-1),
    ENDLOOP(0),
    CIRCLE(1),
    SQUARE(1),
    RECTANGLE(2),
    OVAL(2);

    private final int argumentsNumber;

    Command(int argumentsNumber) {
        this.argumentsNumber = argumentsNumber;
    }

    public int getArgumentsNumber() {

        return argumentsNumber;
    }

    public static Set<String> getCommandNames() {
        return Arrays
                .stream(Command.class.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }
}
