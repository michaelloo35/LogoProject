package pl.edu.agh.to2.dziki.presenter.parser;

import java.util.Arrays;

public enum Command {
    FORWARD(1),
    BACKWARD(1),
    RIGHT(1),
    LEFT(1),
    TURN(1),
    RESTART(0),
    HIDE(0),
    SHOW(0),
    CLEAR(0),
    LIFT(0),
    LOWER(0),
    LOOP(-1),
    ENDLOOP(0),
    CIRCLE(1);

    private final int argumentsNumber;

    Command(int argumentsNumber){
        this.argumentsNumber = argumentsNumber;
    }

    public int getArgumentsNumber() {

        return argumentsNumber;
    }
    public static String[] getCommandNames() {
        return Arrays.stream(Command.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
