package pl.edu.agh.to2.dziki.viewModel.parser;

public enum Command {
    UP(1),
    DOWN(1),
    RIGHT(1),
    LEFT(1),
    TURN(1),
    BACK(0),
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
}
