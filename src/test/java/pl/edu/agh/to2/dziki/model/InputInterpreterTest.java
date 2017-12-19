package pl.edu.agh.to2.dziki.model;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.model.task.complex.Loop;
import pl.edu.agh.to2.dziki.model.task.simple.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class InputInterpreterTest {

    private InputInterpreter inputInterpreter;

    @Before
    public void setUp() {
        inputInterpreter = new InputInterpreter();
    }

    @Test
    public void interpretAndGenerateSimpleTasks1Test() {

        //given
        List<String> validInput = Arrays.asList("FORWARD", "100");

        //when
        List<Task> expectedResult = Arrays.asList(new Forward(100));
        List<Task> output = inputInterpreter.interpretAndGenerateTasks(validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);

    }

    @Test
    public void interpretAndGenerateSimpleTasks2Test() {

        //given
        List<String> validInput = Arrays.asList("TURN", "10", "BACKWARD", "5");

        //when
        List<Task> expectedResult = Arrays.asList(new Turn(10), new Backward(5));
        List<Task> output = inputInterpreter.interpretAndGenerateTasks(validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test
    public void interpretAndGenerateSimpleTasks3Test() {

        //given
        List<String> validInput = Arrays.asList("CIRCLE", "10", "HIDE");

        //when
        List<Task> expectedResult = Arrays.asList(new Circle(10), new Hide());
        List<Task> output = inputInterpreter.interpretAndGenerateTasks(validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test
    public void interpretAndGenerateSimpleTasks4Test() {

        //given
        List<String> validInput = Arrays.asList("SHOW", "HIDE", "LIFT", "LOWER");

        //when
        List<Task> expectedResult = Arrays.asList(new Show(), new Hide(), new Lift(), new Lower());
        List<Task> output = inputInterpreter.interpretAndGenerateTasks(validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test
    public void interpretAndGenerateComplexTasks1Test() {

        //given
        List<String> validInput = Arrays.asList("LOOP", "10", "FORWARD", "10", "ENDLOOP");

        //when
        List<Task> expectedResult = Arrays.asList(new Loop(10, Arrays.asList(new Forward(10))));
        List<Task> output = inputInterpreter.interpretAndGenerateTasks(validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test
    public void interpretAndGenerateComplexTasks2Test() {

        //given
        List<String> validInput = Arrays.asList("LOOP", "10", "FORWARD", "10.5", "TURN", "10", "ENDLOOP");

        //when
        List<Task> expectedResult = Arrays.asList(new Loop(10,
                Arrays.asList(new Forward(10), new Turn(10))));
        List<Task> output = inputInterpreter.interpretAndGenerateTasks(validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test
    public void interpretAndGenerateComplexTasks3Test() {

        //given
        List<String> validInput = Arrays.asList("TURN", "10", "LOOP", "10", "FORWARD",
                "10.5", "TURN", "10", "ENDLOOP", "HIDE");

        //when
        List<Task> expectedResult = Arrays.asList(new Turn(10), new Loop(10,
                Arrays.asList(new Forward(10), new Turn(10))), new Hide());
        List<Task> output = inputInterpreter.interpretAndGenerateTasks(validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test
    public void interpretAndGenerateComplexTasks4Test() {

        //given
        List<String> validInput = Arrays.asList("LOOP", "10", "FORWARD",
                "10.5", "TURN", "10", "ENDLOOP", "LOOP", "50", "RIGHT",
                "123", "CIRCLE", "50.5", "ENDLOOP");

        //when
        List<Task> expectedResult = Arrays.asList(new Loop(10,
                Arrays.asList(new Forward(10), new Turn(10))),
                new Loop(50, Arrays.asList(new Right(123), new Circle(50.5))));
        List<Task> output = inputInterpreter.interpretAndGenerateTasks(validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);
    }


}