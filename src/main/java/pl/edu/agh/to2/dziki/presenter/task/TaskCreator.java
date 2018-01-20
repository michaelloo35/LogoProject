package pl.edu.agh.to2.dziki.presenter.task;

import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.model.task.complex.Loop;
import pl.edu.agh.to2.dziki.model.task.simple.*;
import pl.edu.agh.to2.dziki.presenter.parser.Command;
import pl.edu.agh.to2.dziki.presenter.parser.ValidatedInput;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


public class TaskCreator {


    public List<Task> createTaskList(final Boar boar, ValidatedInput validatedInput) {

        List<Task> taskList = new ArrayList<>();
        int numberOfWordsToSkip;

        List<String> validatedInputCopy = validatedInput.getAsList();

        for (int i = 0; i < validatedInputCopy.size(); i++) {
            Command command = Command.valueOf(validatedInputCopy.get(i));

            if (command.equals(Command.LOOP)) {
                List<String> forSublist = unwrapLoopStatement(validatedInputCopy, i + 2);
                numberOfWordsToSkip = forSublist.size() + 2;
                taskList.add(createLoopTask(boar, parseInt(validatedInputCopy.get(i + 1)), forSublist));
                i += numberOfWordsToSkip;

            } else {
                numberOfWordsToSkip = command.getArgumentsNumber();
                taskList.add(createSimpleTask(boar, validatedInputCopy, command, i + numberOfWordsToSkip));
                i += numberOfWordsToSkip;
            }
        }
        return taskList;
    }

    private Task createSimpleTask(final Boar boar, List<String> input, Command command, int lastParameterIndex) {
        Task returnTask = null;

        double lastParameter = parseDouble(input.get(lastParameterIndex));

        switch (command) {
            case FORWARD:
                returnTask = new Forward(lastParameter, boar);
                break;
            case BACKWARD:
                returnTask = new Backward(lastParameter, boar);
                break;
            case RIGHT:
                returnTask = new Right(lastParameter, boar);
                break;
            case LEFT:
                returnTask = new Left(lastParameter, boar);
                break;
            case ROTATE:
            case TURN:
                returnTask = new Turn(lastParameter, boar);
                break;
            case START:
            case RESTART:
                returnTask = new Restart(boar);
                break;
            case HIDE:
                returnTask = new Hide(boar);
                break;
            case SHOW:
                returnTask = new Show(boar);
                break;
            case CLEAR:
                returnTask = new Clear(boar);
                break;
            case LIFT:
                returnTask = new Lift(boar);
                break;
            case LOWER:
                returnTask = new Lower(boar);
                break;
            case CIRCLE:
                returnTask = new Circle(lastParameter, boar);
                break;
            case SQUARE:
                returnTask = new Square(lastParameter, boar);
                break;
            case RECTANGLE:
                returnTask = new Rectangle(parseDouble(input.get(lastParameterIndex - 1)), lastParameter, boar);
                break;

        }
        return returnTask;
    }

    /**
     * Transforms more complicated to list of simple tasks
     */
    private Task createLoopTask(final Boar boar, int loopIterations, List<String> simpleTasks) {
        List<Task> loopTaskList = new ArrayList<>();

        for (int i = 0; i < simpleTasks.size(); i++) {
            Command command = Command.valueOf(simpleTasks.get(i));
            loopTaskList.add(createSimpleTask(boar, simpleTasks, command, i + command.getArgumentsNumber()));
            i += command.getArgumentsNumber();
        }
        return new Loop(loopIterations, loopTaskList);
    }

    /**
     * Unwraps loop statement example "LOOP 10 MOVE 10 ENDLOOP" --returns--> MOVE 10
     */
    private List<String> unwrapLoopStatement(List<String> input, int index) {
        int endIndex = index;

        for (int i = index; i < input.size(); i++) {
            if (Command.ENDLOOP.toString().equals(input.get(i))) {
                endIndex = i;
                break;
            }
        }
        return input.subList(index, endIndex);
    }


}
