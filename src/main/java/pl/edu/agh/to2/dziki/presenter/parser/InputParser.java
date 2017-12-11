package pl.edu.agh.to2.dziki.presenter.parser;

import java.util.Arrays;
import java.util.List;

public class InputParser {


    /**
     * Validates and parses input string.
     *
     * @param input read from file or textField as string
     * @return List of commands and their arguments as List<String>
     */
    public List<String> parse(String input) {
        return validate(Arrays.asList(input.split("\\s+")));
    }

    /**
     * Throws IllegalArgumentException if input is incorrect.
     */
    private List<String> validate(List<String> input) {
        if (input.isEmpty())
            throw new IllegalArgumentException("Command cannot be empty");
        if (!Arrays.asList(Command.getCommandNames()).contains(input.get(0)))
            throw new IllegalArgumentException("Task has to start with command name");
        if (Command.LOOP.toString().equals(input.get(0)))
            validateComplexTask(input);
        else{
            int argumentsNumber = Command.valueOf(input.get(0)).getArgumentsNumber();
            validateSimpleTask(input, argumentsNumber);
        }
        return input;
    }

    private void validateSimpleTask(List<String> simpleTask, int argumentsNumber) {
        if (simpleTask.size() - 1 != argumentsNumber)
            throw new IllegalArgumentException("Incorrect arguments amount");
        if (argumentsNumber == 1 && Integer.parseInt(simpleTask.get(1)) <= 0)
            throw new IllegalArgumentException("Incorrect argument value");
    }

    private void validateComplexTask(List<String> complexTask) {
        if (complexTask.size() < 4)
            throw new IllegalArgumentException("Incorrect loop syntax");
        if (Integer.parseInt(complexTask.get(1)) <= 0)
            throw new IllegalArgumentException("Loop value has to be a positive value");
        if (!Command.ENDLOOP.toString().equals(complexTask.get(complexTask.size() - 1)))
            throw new IllegalArgumentException("Loop statement has to end with ENDLOOP syntax");

         for(int i = 2; i<complexTask.size() - 1; i++){
             int argumentsNumber = Command.valueOf(complexTask.get(i)).getArgumentsNumber();
             try {
                 validateSimpleTask(complexTask.subList(i, i + argumentsNumber + 1), argumentsNumber);
                 i += argumentsNumber;
             }
             catch (IndexOutOfBoundsException e){
                 throw new IllegalArgumentException("Incorrect simple task syntax in FOR LOOP");
             }
         }

    }
}
