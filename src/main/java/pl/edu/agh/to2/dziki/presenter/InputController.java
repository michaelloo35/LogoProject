package pl.edu.agh.to2.dziki.presenter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.model.task.simple.Restart;
import pl.edu.agh.to2.dziki.presenter.parser.Command;
import pl.edu.agh.to2.dziki.presenter.parser.InputParser;
import pl.edu.agh.to2.dziki.presenter.parser.ValidatedInput;
import pl.edu.agh.to2.dziki.presenter.task.TaskCreator;
import pl.edu.agh.to2.dziki.presenter.undo.TaskExecutor;
import pl.edu.agh.to2.dziki.presenter.utils.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class InputController {

    private static final Logger log = LogManager.getLogger(InputController.class);
    private static final int HISTORY_SIZE = 10;
    private InputParser inputParser;
    private TaskCreator taskCreator;
    private TaskExecutor taskExecutor;
    private InputHistory history;
    private TextAutoFiller autoFiller;
    private ScriptLoader scriptLoader;
    private SnapshotManager snapshotManager;
    private Helper helper;
    private Boar boar;


    @FXML
    private TextField textField;

    @FXML
    private Canvas drawLayer;

    @FXML
    private Canvas boarLayer;

    @FXML
    private TextArea textArea;


    @FXML
    public void initialize() {
        inputParser = new InputParser();
        taskCreator = new TaskCreator();
        boar = new Boar();

        // setup boar initialization
        List<Task> setupTasks = boarSetupTasks();

        taskExecutor = new TaskExecutor(setupTasks.size());
        new ViewUpdater(boarLayer, drawLayer, boar, taskExecutor);
        history = new InputHistory(HISTORY_SIZE);
        autoFiller = new TextAutoFiller(Command.getCommandNames());
        scriptLoader = new ScriptLoader(inputParser, taskCreator, taskExecutor, boar);
        snapshotManager = new SnapshotManager(drawLayer);
        helper = new Helper();

        // initialize boar
        taskExecutor.executeTasks(setupTasks);

    }

    /**
     * @return List of tasks to be executed on application startup to setup initial boar state
     */
    private List<Task> boarSetupTasks() {
        List<Task> initializationTasks = new ArrayList<>();
        initializationTasks.add(new Restart((boar)));
        return initializationTasks;
    }

    @FXML
    public void onKeyPressed(KeyEvent ke) {
        switch (ke.getCode()) {

            case ENTER:
                enterHandler();
                break;

            case UP:
                arrowUpHandler();
                break;

            case DOWN:
                arrowDownHandler();
                break;
            case TAB:
                tabHandler();
                // consuming event in order to disable tab navigation feature
                ke.consume();
                break;
        }

    }

    private void tabHandler() {
        String message;
        if ((message = textField.getText()).isEmpty())
            return;
        String[] words = message.split("\\s+");
        if (words.length == 0)
            return;

        // extracting last word from string since that is what we are trying to auto fill
        String fillerString;

        if ((fillerString = autoFiller.fillOrGetNextMatch(words[words.length - 1])) == null)
            return;
        words[words.length - 1] = fillerString;
        textField.setText(String.join(" ", words).toLowerCase());

        // updating caret position
        Platform.runLater(() -> textField.positionCaret(textField.getText().length()));

    }

    private void arrowDownHandler() {
        String nextMessage;
        if ((nextMessage = history.getNext()) != null) textField.setText(nextMessage);
        // updating caret position
        Platform.runLater(() -> textField.positionCaret(textField.getText().length()));
    }

    private void arrowUpHandler() {
        String previousMessage;
        if ((previousMessage = history.getPrevious()) != null) textField.setText(previousMessage);
        // updating caret position
        Platform.runLater(() -> textField.positionCaret(textField.getText().length()));
    }

    private void enterHandler() {
        String message = textField.getText();
        textArea.appendText(message + "\n");
        history.add(message);

        try {
            ValidatedInput validatedInput = inputParser.validate(inputParser.parse(message));
            List<Task> tasks = taskCreator.createTaskList(boar, validatedInput);
            taskExecutor.executeTasks(tasks);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            printUserError(e);
        }

        textField.clear();
    }

    private void printUserError(Exception e) {
        textArea.appendText("******************ERROR******************\n");
        textArea.appendText(e.getMessage() + "\n");
        textArea.appendText("******************ERROR******************\n");
    }

    public void undoButtonHandler(ActionEvent actionEvent) {
        taskExecutor.undo();
    }

    public void fileButtonHandler() {
        try {
            scriptLoader.load();
            textArea.appendText("*************Script executed*************\n");
        }
        catch (ParseException e){
            printUserError(e);
        }
    }

    public void snapshotButtonHandler() {
        try {
            snapshotManager.saveAsPng();
        } catch (IOException e) {
            log.error("Could not save snapshot");
        }
    }

    public void helpButtonHandler() {
        helper.popupHelp();
    }
}
