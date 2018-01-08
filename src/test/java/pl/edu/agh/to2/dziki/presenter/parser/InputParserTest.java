package pl.edu.agh.to2.dziki.presenter.parser;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InputParserTest {

    private InputParser inputParser;

    @Before
    public void setUp() throws Exception {
        inputParser = new InputParser();
    }

    @Test
    public void shouldParseAndValidate() throws Exception {

        //given
        String input = "TURN 90";

        //when
        List<String> expectedResult = Arrays.asList("TURN", "90");
        List<String> output = inputParser.validate(inputParser.parse(input)).getAsList();

        //then
        assertThat(output).containsAll(expectedResult);
        assertThat(output).hasSameSizeAs(expectedResult);

    }

    @Test
    public void shouldParseAndValidateComplexTask() throws Exception {

        //given
        String input1 = "LOOP 100 FORWARD 20 TURN 30 ENDLOOP";
        String input2 = "forward 10 turn -20 loop 5 left 10 endloop";
        String input3 = "left 10 right 20 forward 30 backward 40";
        String input4 = "left 10 restart circle 50 lift forward 5 lower";

        //when
        List<String> expectedResult1 = Arrays.asList("LOOP", "100", "FORWARD", "20",
                "TURN", "30", "ENDLOOP");
        List<String> expectedResult2 = Arrays.asList("FORWARD", "10", "TURN", "-20",
                "LOOP", "5", "LEFT", "10", "ENDLOOP");
        List<String> expectedResult3 = Arrays.asList("LEFT", "10", "RIGHT", "20",
                "FORWARD", "30", "BACKWARD", "40");
        List<String> expectedResult4 = Arrays.asList("LEFT", "10", "RESTART", "CIRCLE",
                "50", "LIFT", "FORWARD", "5", "LOWER");

        List<String> output1 = inputParser.validate(inputParser.parse(input1)).getAsList();
        List<String> output2 = inputParser.validate(inputParser.parse(input2)).getAsList();
        List<String> output3 = inputParser.validate(inputParser.parse(input3)).getAsList();
        List<String> output4 = inputParser.validate(inputParser.parse(input4)).getAsList();

        //then
        assertThat(output1).containsAll(expectedResult1);
        assertThat(output1).hasSameSizeAs(expectedResult1);

        assertThat(output2).containsAll(expectedResult2);
        assertThat(output2).hasSameSizeAs(expectedResult2);

        assertThat(output3).containsAll(expectedResult3);
        assertThat(output3).hasSameSizeAs(expectedResult3);

        assertThat(output4).containsAll(expectedResult4);
        assertThat(output4).hasSameSizeAs(expectedResult4);

    }

    @Test
    public void shouldParseAndValidateSimpleTask() throws Exception {

        //given
        String input1 = "FORWARD 20";
        String input2 = "BACKWARD 90";
        String input3 = "CLEAR";
        String input4 = "TURN 10";

        //when
        List<String> expectedResult = Arrays.asList("FORWARD", "20");
        List<String> expectedResult2 = Arrays.asList("BACKWARD", "90");
        List<String> expectedResult3 = Arrays.asList("CLEAR");
        List<String> expectedResult4 = Arrays.asList("TURN", "10");

        List<String> output1 = inputParser.validate(inputParser.parse(input1)).getAsList();
        List<String> output2 = inputParser.validate(inputParser.parse(input2)).getAsList();
        List<String> output3 = inputParser.validate(inputParser.parse(input3)).getAsList();
        List<String> output4 = inputParser.validate(inputParser.parse(input4)).getAsList();

        //then
        assertThat(output1).containsAll(expectedResult);
        assertThat(output1).hasSameSizeAs(expectedResult);

        assertThat(output2).containsAll(expectedResult2);
        assertThat(output2).hasSameSizeAs(expectedResult2);

        assertThat(output3).containsAll(expectedResult3);
        assertThat(output3).hasSameSizeAs(expectedResult3);

        assertThat(output4).containsAll(expectedResult4);
        assertThat(output4).hasSameSizeAs(expectedResult4);

    }

    @Test
    public void shouldParseLowerCaseInput() {

        //given
        String input = "right 10 hide left 10 show";

        //when
        List<String> expectedResult = Arrays.asList("RIGHT", "10", "HIDE", "LEFT",
                "10", "SHOW");
        List<String> output = inputParser.validate(inputParser.parse(input)).getAsList();

        //then
        assertThat(output).containsAll(expectedResult);
        assertThat(output).hasSameSizeAs(expectedResult);

    }

    @Test
    public void shouldParseAndValidateLowerUpperCaseInput() {

        //given
        String input = "LeFt 10 HIde ForWARd 5";

        //when
        List<String> expectedResult = Arrays.asList("LEFT", "10", "HIDE", "FORWARD", "5");
        List<String> output = inputParser.validate(inputParser.parse(input)).getAsList();

        //then
        assertThat(output).containsAll(expectedResult);
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test
    public void shouldParseAndValidateDecimalValuesInCommand1() {

        //given
        String input = "LEFT 10.6 HIDE FORWARD 5.2";

        //when
        List<String> expectedResult = Arrays.asList("LEFT", "10.6", "HIDE", "FORWARD",
                "5.2");
        List<String> output = inputParser.validate(inputParser.parse(input)).getAsList();

        //then
        assertThat(output).containsAll(expectedResult);
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test
    public void shouldParseAndValidateDecimalValuesInCommand12() {

        //given
        String input = "LOOP 2 FORWARD 25.12 ENDLOOP";

        //when
        List<String> expectedResult = Arrays.asList("LOOP", "2", "FORWARD", "25.12",
                "ENDLOOP");
        List<String> output = inputParser.validate(inputParser.parse(input)).getAsList();

        //then
        assertThat(output).containsAll(expectedResult);
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateIncorrectComplex() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("LOOP 100 FORWARD 20 30 ENDLOOP");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateIncorrectSimple() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("CLEAR 20");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateEmptyInput() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateUnrecognizableCommand() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("DOWN 20");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateUnrecognizableCommandInComplexTask() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("LOOP 20 FORWARD 10 RIGHT 5 TAB 50 ENDLOOP");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateIncorrectArgumentAmount1() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("CIRCLE 5 10");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateIncorrectArgumentAmount2() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("LOOP 5 FORWARD 5 CIRCLE 1 2 ENDLOOP");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateStartWithEndloopStatement() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("ENDLOOP TURN 50 FORWARD 100");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateNegativeIterationsValue() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("LOOP -2 TURN 2 ENDLOOP");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateNotClosedLoopStatement1() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("LOOP 10 TURN 2 BACKWARD 20 CIRCLE 5");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateNotClosedLoopStatement2() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("LOOP 10 TURN 2 BACKWARD 20 CIRCLE 5 LOOP 5 TURN 5 ENDLOOP");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateDecimalIterationValue() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("LOOP 10.5 TURN 2 BACKWARD 20 CIRCLE 5 ENDLOOP");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateNegativeCommandValue1() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("LOOP 2 TURN 2 FORWARD -20 ENDLOOP");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateIncorrectIterationValueAmount() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("LOOP 5 10 FORWARD 5 CIRCLE 1 2 ENDLOOP");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateNegativeCommandValue2() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("CIRCLE -50.5");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateNotNumericCommandAttribute1() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("CIRCLE XD");

        //when
        inputParser.validate(parsedInput);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotValidateNotNumericCommandAttribute2() throws Exception {

        //given
        ParsedInput parsedInput = inputParser.parse("LOOP 2 TURN T FORWARD F ENDLOOP");

        //when
        inputParser.validate(parsedInput);

        //then
    }


}