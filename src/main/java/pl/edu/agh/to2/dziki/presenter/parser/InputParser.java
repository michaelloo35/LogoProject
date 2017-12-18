package pl.edu.agh.to2.dziki.presenter.parser;

import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

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
        input.replaceAll(String::toUpperCase);
        for (int i = 0; i < input.size(); i++) {
            if (Command.LOOP.toString().equals(input.get(i))) {
                List<String> loopSublist = loopSublistExtraction(input, i);
                validateComplexTask(loopSublist);
                i += loopSublist.size() - 1;
            } else {
                int argumentsNumber;
                try {
                    argumentsNumber = Command.valueOf(input.get(i)).getArgumentsNumber();
                }
                catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Unrecognizable command name " + input.get(i));
                }
                validateSimpleTask(input.subList(i, i + argumentsNumber + 1), argumentsNumber);
                i += argumentsNumber;

            }
        }
        return input;
    }

    private void validateSimpleTask(List<String> simpleTask, int argumentsNumber) {
        System.out.println("*******" + simpleTask + "*********");
        if (simpleTask.size() - 1 != argumentsNumber)
            throw new IllegalArgumentException("Incorrect arguments amount");

        try {
            if (Command.ENDLOOP.toString().equals(simpleTask.get(0)))
                throw new IllegalArgumentException("ENDLOOP cannot start a statement");
            for(int i = 1; i <= argumentsNumber; i++){
                if(!Command.TURN.toString().equals(simpleTask.get(0)) && parseDouble(simpleTask.get(i)) <= 0)
                    throw new IllegalArgumentException("Argument has to be positive value");
                else
                    parseDouble(simpleTask.get(i));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect value \"" + simpleTask.get(1) + "\" should be integer");
        }
    }

    private void validateComplexTask(List<String> complexTask) {
        if (complexTask.size() <= 4)
            throw new IllegalArgumentException("Loop syntax too short");

        if (!isNumeric(complexTask.get(1)))
            throw new IllegalArgumentException("Loop value has to be a numeric value");

        if (parseInt(complexTask.get(1)) <= 0)
            throw new IllegalArgumentException("Loop value has to be a positive value");

        if (!Command.ENDLOOP.toString().equals(complexTask.get(complexTask.size() - 1)))
            throw new IllegalArgumentException("Loop statement has to end with ENDLOOP syntax");

        for (int i = 2; i < complexTask.size() - 1; i++) {
            try {
                int argumentsNumber = Command.valueOf(complexTask.get(i)).getArgumentsNumber();
                validateSimpleTask(complexTask.subList(i, i + argumentsNumber + 1), argumentsNumber);
                i += argumentsNumber;
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Incorrect simple task syntax in FOR LOOP");
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unrecognizable command syntax in loop statement");
            }
        }

    }

    private List<String> loopSublistExtraction(List<String> input, int startIndex) {
        int endIndex = startIndex;
        for (int i = startIndex + 1; i < input.size(); i++) {
            if (Command.ENDLOOP.toString().equals(input.get(i))) {
                endIndex = i;
                break;
            } else if (Command.LOOP.toString().equals(input.get(i)))
                throw new IllegalArgumentException("There is no ENDLOOP statement." +
                        " Next LOOP statement was encountered");
        }
        if (endIndex != startIndex)
            return input.subList(startIndex, endIndex + 1);
        else
            throw new IllegalArgumentException("There is no ENDLOOP statement");

    }

    private boolean isNumeric(String value) {
        return value.matches("-?\\d+(\\.\\d+)?");
    }
}
