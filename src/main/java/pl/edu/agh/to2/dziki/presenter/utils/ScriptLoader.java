package pl.edu.agh.to2.dziki.presenter.utils;

import javafx.stage.FileChooser;
import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.parser.InputParser;
import pl.edu.agh.to2.dziki.presenter.parser.ValidatedInput;
import pl.edu.agh.to2.dziki.presenter.task.TaskCreator;
import pl.edu.agh.to2.dziki.presenter.undo.TaskExecutor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Stream;

public class ScriptLoader {

    private final InputParser inputParser;
    private final TaskCreator taskCreator;
    private final TaskExecutor taskExecutor;
    private final Boar boar;

    public ScriptLoader(InputParser inputParser, TaskCreator taskCreator, TaskExecutor taskExecutor, Boar boar) {
        this.inputParser = inputParser;
        this.taskCreator = taskCreator;
        this.taskExecutor = taskExecutor;
        this.boar = boar;
    }


    public void load() throws ParseException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose LOGO script");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            try (Stream<String> file = Files.lines(Paths.get(selectedFile.toURI()))) {

                for (String line : (Iterable<String>) file::iterator) {
                    ValidatedInput validatedInput = inputParser.validate(inputParser.parse(line));
                    List<Task> tasks = taskCreator.createTaskList(boar, validatedInput);
                    taskExecutor.executeTasks(tasks);
                }
            } catch (IOException | IllegalArgumentException | IndexOutOfBoundsException e) {
                throw new ParseException(e.getMessage(), 0);
            }
        }
    }

}
