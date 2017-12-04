package pl.edu.agh.to2.dziki.viewModel.parser;

import org.junit.Test;
import sun.reflect.annotation.ExceptionProxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class InputParserTest {

    private InputParser inputParser = new InputParser();

    @Test
    public void parseTest() throws Exception {
        String input = "TURN 90";
        List<String> expectedResult = Arrays.asList("TURN", "90");

        assertThat(inputParser.parse(input), is(expectedResult));

    }

    @Test
    public void parseComplexTaskTest() throws Exception {
        String input = "LOOP 100 UP 20 TURN 30 ENDLOOP";
        List<String> expectedResult = Arrays.asList("LOOP", "100", "UP", "20",
                "TURN", "30", "ENDLOOP");

        assertThat(inputParser.parse(input), is(expectedResult));

    }

    @Test
    public void parseSimpleTaskTest() throws Exception {
        String input = "UP 20";
        String input2 = "DOWN 90";
        String input3 = "CLEAR";
        List<String> expectedResult = Arrays.asList("UP", "20");
        List<String> expectedResult2 = Arrays.asList("DOWN", "90");
        List<String> expectedResult3 = Arrays.asList("CLEAR");

        assertThat(inputParser.parse(input), is(expectedResult));
        assertThat(inputParser.parse(input2), is(expectedResult2));
        assertThat(inputParser.parse(input3), is(expectedResult3));

    }

    @Test(expected = IllegalArgumentException.class)
    public void parseIncorrectComplexTest() throws Exception{
        String input = "LOOP 100 UP 20 30 ENDLOOP";

        List<String> output = inputParser.parse(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseIncorrectSimpleTest() throws Exception{
        String input = "CLEAR 20";

        List<String> output = inputParser.parse(input);
    }

}