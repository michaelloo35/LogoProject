package pl.edu.agh.to2.dziki.model;

import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.model.task.complex.Loop;
import pl.edu.agh.to2.dziki.model.task.simple.*;
import pl.edu.agh.to2.dziki.presenter.parser.Command;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


public class InputInterpreter {

    public List<Task> interpretAndGenerateTasks(List<String> validatedInput) {

        List<Task> taskList = new ArrayList<>();
        int skipIterations;

        for (int i = 0; i < validatedInput.size(); i++) {
            Command command = Command.valueOf(validatedInput.get(i));

            if (command.equals(Command.LOOP)) {
                List<String> forSublist = unwrapLoopStatement(validatedInput, i + 2);
                skipIterations = forSublist.size() + 2;
                taskList.add(createLoopTask(parseInt(validatedInput.get(i + 1)), forSublist));
                i += skipIterations;

            } else {
                skipIterations = command.getArgumentsNumber();
                taskList.add(createSimpleTask(validatedInput, command, i + skipIterations));
                i += skipIterations;
            }
        }
        return taskList;
    }

    private Task createSimpleTask(List<String> input, Command command, int commandParameterIndex) {
        Task returnTask = null;

        switch (command) {
            case FORWARD:
                returnTask = new Forward(parseDouble(input.get(commandParameterIndex)));
                break;
            case BACKWARD:
                returnTask = new Backward(parseDouble(input.get(commandParameterIndex)));
                break;
            case RIGHT:
                returnTask = new Right(parseDouble(input.get(commandParameterIndex)));
                break;
            case LEFT:
                returnTask = new Left(parseDouble(input.get(commandParameterIndex)));
                break;
            case TURN:
                returnTask = new Turn(parseDouble(input.get(commandParameterIndex)));
                break;
            case RESTART:
                returnTask = new Restart();
                break;
            case HIDE:
                returnTask = new Hide();
                break;
            case SHOW:
                returnTask = new Show();
                break;
            case LIFT:
                returnTask = new Lift();
                break;
            case LOWER:
                returnTask = new Lower();
                break;
            case CIRCLE:
                returnTask = new Circle(parseDouble(input.get(commandParameterIndex)));
                break;
        }
        return returnTask;
    }

    /**
     * Transforms more complicated to list of simple tasks
     */
    private Task createLoopTask(int loopIterations, List<String> simpleTasks) {
        List<Task> loopTaskList = new ArrayList<>();

        for (int i = 0; i < simpleTasks.size(); i++) {
            Command command = Command.valueOf(simpleTasks.get(i));
            loopTaskList.add(createSimpleTask(simpleTasks, command, i + command.getArgumentsNumber()));
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
