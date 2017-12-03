package pl.edu.agh.to2.dziki.model;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    public List<String> parse(String input) {

        return validate(Arrays.asList(input.split("\\s+")));
    }

    private List<String> validate(List<String> input) {

        return input;
    }
}
