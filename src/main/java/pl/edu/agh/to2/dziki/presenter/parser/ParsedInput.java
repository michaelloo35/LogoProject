package pl.edu.agh.to2.dziki.presenter.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Immutable class
 */
public class ParsedInput {
    private final List<String> parsedInput;

    public ParsedInput(List<String> parsedInput) {
        this.parsedInput = parsedInput;
    }

    public List<String> getAsList() {
        return new ArrayList<>(parsedInput);
    }
}
