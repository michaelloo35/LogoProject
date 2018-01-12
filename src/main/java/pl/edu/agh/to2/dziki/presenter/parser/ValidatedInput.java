package pl.edu.agh.to2.dziki.presenter.parser;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Immutable class
 */
public class ValidatedInput {
    private final List<String> validatedInput;

    public ValidatedInput(@NotNull List<String> validatedInput) {
        this.validatedInput = validatedInput;
    }

    public List<String> getAsList() {
        return new ArrayList<>(validatedInput);
    }
}
