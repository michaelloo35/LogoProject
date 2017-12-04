package pl.edu.agh.to2.dziki.viewModel.parser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class InputParserTest {
    @Test
    public void parseTest() throws Exception {
        String input = "TURN 90";
        InputParser inputParser = new InputParser();

        List<String> expectedResult = Arrays.asList("TURN", "90");

        assertThat(inputParser.parse(input), is(expectedResult));

    }

}