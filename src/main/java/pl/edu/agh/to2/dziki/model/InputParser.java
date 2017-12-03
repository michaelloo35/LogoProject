package pl.edu.agh.to2.dziki.model;

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

        return input;
    }
}
