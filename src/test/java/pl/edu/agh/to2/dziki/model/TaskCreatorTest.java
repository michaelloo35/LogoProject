package pl.edu.agh.to2.dziki.model;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.model.task.complex.Loop;
import pl.edu.agh.to2.dziki.model.task.simple.*;
import pl.edu.agh.to2.dziki.presenter.parser.ValidatedInput;
import pl.edu.agh.to2.dziki.presenter.task.TaskCreator;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;


public class TaskCreatorTest {

    private TaskCreator taskCreator;
    private Boar boar;

    @Before
    public void setUp() {
        taskCreator = new TaskCreator();
        boar = mock(Boar.class);
    }


    @Test
    public void interpretAndGenerateSimpleTasks1Test() {

        //given
        ValidatedInput validInput = new ValidatedInput(asList("FORWARD", "100"));

        //when
        List<Task> expectedResult = asList(new Forward(100, boar));
        List<Task> output = taskCreator.createTaskList(boar, validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);

    }

    @Test
    public void interpretAndGenerateSimpleTasks2Test() {

        //given
        ValidatedInput validInput = new ValidatedInput(asList("TURN", "10", "BACKWARD", "5"));

        //when
        List<Task> expectedResult = asList(new Turn(10, boar), new Backward(5, boar));
        List<Task> output = taskCreator.createTaskList(boar, validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test
    public void interpretAndGenerateSimpleTasks3Test() {

        //given
        ValidatedInput validInput = new ValidatedInput(asList("CIRCLE", "10", "HIDE"));

        //when
        List<Task> expectedResult = asList(new Circle(10, boar), new Hide(boar));
        List<Task> output = taskCreator.createTaskList(boar, validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test
    public void interpretAndGenerateSimpleTasks4Test() {

        //given
        ValidatedInput validInput = new ValidatedInput(asList("SHOW", "HIDE", "LIFT", "LOWER"));

        //when
        List<Task> expectedResult = asList(new Show(boar), new Hide(boar), new Lift(boar), new Lower(boar));
        List<Task> output = taskCreator.createTaskList(boar, validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test
    public void interpretAndGenerateComplexTasks1Test() {

        //given
        ValidatedInput validInput = new ValidatedInput(asList("LOOP", "10", "FORWARD", "10", "ENDLOOP"));

        //when
        List<Task> expectedResult = asList(new Loop(10, asList(new Forward(10, boar))));
        List<Task> output = taskCreator.createTaskList(boar, validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test
    public void interpretAndGenerateComplexTasks2Test() {

        //given
        ValidatedInput validInput = new ValidatedInput(asList("LOOP", "10", "FORWARD", "10.5", "TURN", "10", "ENDLOOP"));

        //when
        List<Task> expectedResult = asList(
                new Loop(10, asList(new Forward(10, boar), new Turn(10, boar))));
        List<Task> output = taskCreator.createTaskList(boar, validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test
    public void interpretAndGenerateComplexTasks3Test() {

        //given
        ValidatedInput validInput = new ValidatedInput(asList("TURN", "10", "LOOP", "10", "FORWARD",
                "10.5", "TURN", "10", "ENDLOOP", "HIDE"));

        //when
        List<Task> expectedResult = asList(new Turn(10, boar), new Loop(10,
                asList(new Forward(10, boar), new Turn(10, boar))), new Hide(boar));
        List<Task> output = taskCreator.createTaskList(boar, validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);
    }

    @Test
    public void interpretAndGenerateComplexTasks4Test() {

        //given
        ValidatedInput validInput = new ValidatedInput(asList("LOOP", "10", "FORWARD",
                "10.5", "TURN", "10", "ENDLOOP", "LOOP", "50", "RIGHT",
                "123", "CIRCLE", "50.5", "ENDLOOP"));

        //when
        List<Task> expectedResult = asList(new Loop(10,
                        asList(new Forward(10, boar), new Turn(10, boar))),
                new Loop(50, asList(new Right(123, boar), new Circle(50.5, boar))));
        List<Task> output = taskCreator.createTaskList(boar, validInput);

        //then
        for (int i = 0; i < output.size(); i++)
            assertThat(output.get(i)).isEqualsToByComparingFields(expectedResult.get(i));
        assertThat(output).hasSameSizeAs(expectedResult);
    }


}