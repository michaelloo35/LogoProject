package pl.edu.agh.to2.dziki.model;

import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.model.task.complex.Loop;
import pl.edu.agh.to2.dziki.model.task.simple.*;
import pl.edu.agh.to2.dziki.presenter.parser.Command;

import java.util.ArrayList;
import java.util.List;


public class InputInterpreter {

    private List<String> validatedInput;

    public InputInterpreter(List<String> validatedInput) {
        this.validatedInput = validatedInput;
    }


    public List<Task> convertInputToTaskList() {
        List<Task> taskList = new ArrayList<>();
        int skipIterations;
        for (int i = 0; i < this.validatedInput.size(); i++) {
            Command command = Command.valueOf(this.validatedInput.get(i));
            if (command.equals(Command.LOOP)) {
                List<String> forSublist = getLoopSublist(i + 1);
                skipIterations = forSublist.size() + 2;
                taskList.add(getLoopTaskInstance(Integer.parseInt(validatedInput.get(i + 1)),
                        forSublist));
                i += skipIterations;
            }
            else {
                skipIterations = command.getArgumentsNumber();
                taskList.add(getTaskInstance(command, i + skipIterations));
                i += skipIterations;
            }
        }
        return taskList;
    }

    private List<String> getLoopSublist(int index) {
        int endIndex = index;
        for (int i = index; i < this.validatedInput.size(); i++) {
            if (validatedInput.get(i).toUpperCase().equals("ENDLOOP")) {
                endIndex = i;
                break;
            }
        }
        return this.validatedInput.subList(index, endIndex);
    }

    private double getParemeterValue(int index) {
        return Double.parseDouble(this.validatedInput.get(index));
    }


    private Task getLoopTaskInstance(int loopIterations, List<String> simpleTasks) {
        List<Task> loopTaskList = new ArrayList<>();
        for (int i = 0; i < simpleTasks.size(); i++) {
            Command command = Command.valueOf(simpleTasks.get(i));
            loopTaskList.add(getTaskInstance(command, i + command.getArgumentsNumber()));
            i += command.getArgumentsNumber();
        }
        return new Loop(loopIterations, loopTaskList);
    }

    private Task getTaskInstance(Command command, int index) {
        Task returnTask = null;
        switch (command) {
            case FORWARD:
                returnTask = new Forward(getParemeterValue(index));
                break;
            case BACKWARD:
                returnTask = new Backward(getParemeterValue(index));
                break;
            case RIGHT:
                returnTask = new Right(getParemeterValue(index));
                break;
            case LEFT:
                returnTask = new Left(getParemeterValue(index));
                break;
            case TURN:
                returnTask = new Turn(getParemeterValue(index));
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
                returnTask = new Circle(getParemeterValue(index));
        }
        return returnTask;
    }
}
