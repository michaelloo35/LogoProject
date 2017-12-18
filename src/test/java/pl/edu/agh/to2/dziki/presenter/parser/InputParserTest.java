package pl.edu.agh.to2.dziki.presenter.parser;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InputParserTest {

    private InputParser inputParser;
    @Before
    public void setUp() throws Exception{
        inputParser = new InputParser();
    }

    @Test
    public void parseTest() throws Exception {

        //given
        String input = "TURN 90";

        //when
        List<String> expectedResult = Arrays.asList("TURN", "90");
        List<String> output = inputParser.parse(input);

        //then
        assertThat(output).containsAll(expectedResult);
        assertThat(output).hasSameSizeAs(expectedResult);

    }

    @Test
    public void parseComplexTaskTest() throws Exception {

        //given
        String input = "LOOP 100 FORWARD 20 TURN 30 ENDLOOP";
        String input2 = "forward 10 turn -20 loop 5 left 10 endloop";
        String input3 = "left 10 right 20 forward 30 backward 40";
        String input4 = "left 10 restart circle 50 lift forward 5 lower";

        //when
        List<String> expectedResult = Arrays.asList("LOOP", "100", "FORWARD", "20",
                "TURN", "30", "ENDLOOP");
        List<String> expectedResult2 = Arrays.asList("FORWARD", "10", "TURN", "-20",
                "LOOP", "5", "LEFT", "10", "ENDLOOP");
        List<String> expectedResult3 = Arrays.asList("LEFT", "10", "RIGHT", "20",
                "FORWARD", "30", "BACKWARD", "40");
        List<String> expectedResult4 = Arrays.asList("LEFT", "10", "RESTART", "CIRCLE",
                "50", "LIFT", "FORWARD", "5", "LOWER");

        List<String> output = inputParser.parse(input);
        List<String> output2 = inputParser.parse(input2);
        List<String> output3 = inputParser.parse(input3);
        List<String> output4 = inputParser.parse(input4);

        //then
        assertThat(output).containsAll(expectedResult);
        assertThat(output).hasSameSizeAs(expectedResult);

        assertThat(output2).containsAll(expectedResult2);
        assertThat(output2).hasSameSizeAs(expectedResult2);

        assertThat(output3).containsAll(expectedResult3);
        assertThat(output3).hasSameSizeAs(expectedResult3);

        assertThat(output4).containsAll(expectedResult4);
        assertThat(output4).hasSameSizeAs(expectedResult4);

    }

    @Test
    public void parseSimpleTaskTest() throws Exception {

        //given
        String input = "FORWARD 20";
        String input2 = "BACKWARD 90";
        String input3 = "CLEAR";
        String input4 = "TURN 10";

        //when
        List<String> expectedResult = Arrays.asList("FORWARD", "20");
        List<String> expectedResult2 = Arrays.asList("BACKWARD", "90");
        List<String> expectedResult3 = Arrays.asList("CLEAR");
        List<String> expectedResult4 = Arrays.asList("TURN", "10");
        List<String> output = inputParser.parse(input);
        List<String> output2 = inputParser.parse(input2);
        List<String> output3 = inputParser.parse(input3);
        List<String> output4 = inputParser.parse(input4);

        //then
        assertThat(output).containsAll(expectedResult);
        assertThat(output).hasSameSizeAs(expectedResult);

        assertThat(output2).containsAll(expectedResult2);
        assertThat(output2).hasSameSizeAs(expectedResult2);

        assertThat(output3).containsAll(expectedResult3);
        assertThat(output3).hasSameSizeAs(expectedResult3);

        assertThat(output4).containsAll(expectedResult4);
        assertThat(output4).hasSameSizeAs(expectedResult4);

    }
    @Test
    public void parseLowerCaseInputTest(){

        //given
        String input = "right 10 hide left 10 show";

        //when
        List<String> expectedResult = Arrays.asList("RIGHT", "10", "HIDE", "LEFT",
                "10", "SHOW");
        List<String> output = inputParser.parse(input);

        //then
        assertThat(output).containsAll(expectedResult);
        assertThat(output).hasSameSizeAs(expectedResult);

    }

    @Test
    public void parseLowerUpperCaseInputTest(){

        //given
        String input = "LeFt 10 HIde ForWARd 5";

        //when
        List<String> expectedResult = Arrays.asList("LEFT", "10", "HIDE", "FORWARD",
                "5");
        List<String> output = inputParser.parse(input);

        //then
        assertThat(output).containsAll(expectedResult);
        assertThat(output).hasSameSizeAs(expectedResult);
    }
    @Test
    public void parseDecimalValuesInCommand1Test(){

        //given
        String input = "LEFT 10.6 HIDE FORWARD 5.2";

        //when
        List<String> expectedResult = Arrays.asList("LEFT", "10.6", "HIDE", "FORWARD",
                "5.2");
        List<String> output = inputParser.parse(input);

        //then
        assertThat(output).containsAll(expectedResult);
        assertThat(output).hasSameSizeAs(expectedResult);
    }
    @Test
    public void parseDecimalValuesInCommand12Test(){

        //given
        String input = "LOOP 2 FORWARD 25.12 ENDLOOP";

        //when
        List<String> expectedResult = Arrays.asList("LOOP", "2", "FORWARD", "25.12",
                "ENDLOOP");
        List<String> output = inputParser.parse(input);

        //then
        assertThat(output).containsAll(expectedResult);
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseIncorrectComplexTest() throws Exception{
        //given
        String input = "LOOP 100 FORWARD 20 30 ENDLOOP";

        //when
        inputParser.parse(input);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseIncorrectSimpleTest() throws Exception{
        //given
        String input = "CLEAR 20";

        //when
        inputParser.parse(input);

        //then
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseEmptyInputTest() throws Exception{
        //given
        String input = "";

        //when
        inputParser.parse(input);

        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseUnrecognizableCommandTest() throws Exception{
        //given
        String input = "DOWN 20";

        //when
        inputParser.parse(input);

        //then
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseUnrecognizableCommandInComplexTaskTest() throws Exception{
        //given
        String input = "LOOP 20 FORWARD 10 RIGHT 5 TAB 50 ENDLOOP";

        //when
        inputParser.parse(input);

        //then
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseIncorrectArgumentAmount1Test() throws Exception{
        //given
        String input = "CIRCLE 5 10";

        //when
        inputParser.parse(input);

        //then
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseIncorrectArgumentAmount2Test() throws Exception{
        //given
        String input = "LOOP 5 FORWARD 5 CIRCLE 1 2 ENDLOOP";

        //when
        inputParser.parse(input);

        //then
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseStartWithEndloopStatementTest() throws Exception{
        //given
        String input = "ENDLOOP TURN 50 FORWARD 100";

        //when
        inputParser.parse(input);

        //then
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseNegativeIterationsValueTest() throws Exception{
        //given
        String input = "LOOP -2 TURN 2 ENDLOOP";

        //when
        inputParser.parse(input);

        //then
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseNotClosedLoopStatement1Test() throws Exception{
        //given
        String input = "LOOP 10 TURN 2 BACKWARD 20 CIRCLE 5";

        //when
        inputParser.parse(input);

        //then
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseNotClosedLoopStatement2Test() throws Exception{
        //given
        String input = "LOOP 10 TURN 2 BACKWARD 20 CIRCLE 5 LOOP 5 TURN 5 ENDLOOP";

        //when
        inputParser.parse(input);

        //then
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseDecimalIterationValueTest() throws Exception{
        //given
        String input = "LOOP 10.5 TURN 2 BACKWARD 20 CIRCLE 5 ENDLOOP";

        //when
        inputParser.parse(input);

        //then
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseNegativeCommandValue1Test() throws Exception{
        //given
        String input = "LOOP 2 TURN 2 FORWARD -20 ENDLOOP";

        //when
        inputParser.parse(input);

        //then
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseIncorrectIterationValueAmountTest() throws Exception{
        //given
        String input = "LOOP 5 10 FORWARD 5 CIRCLE 1 2 ENDLOOP";

        //when
        inputParser.parse(input);

        //then
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseNegativeCommandValue2Test() throws Exception{
        //given
        String input = "CIRCLE -50.5";

        //when
        inputParser.parse(input);

        //then
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseNotNumericCommandAttribute1Test() throws Exception{
        //given
        String input = "CIRCLE XD";

        //when
        inputParser.parse(input);

        //then
    }
    @Test(expected = IllegalArgumentException.class)
    public void parseNotNumericCommandAttribute2Test() throws Exception{
        //given
        String input = "LOOP 2 TURN T FORWARD F ENDLOOP";

        //when
        inputParser.parse(input);

        //then
    }




}