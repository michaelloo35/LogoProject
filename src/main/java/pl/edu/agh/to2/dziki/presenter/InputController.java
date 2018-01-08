package pl.edu.agh.to2.dziki.presenter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.parser.Command;
import pl.edu.agh.to2.dziki.presenter.parser.InputParser;
import pl.edu.agh.to2.dziki.presenter.parser.ValidatedInput;
import pl.edu.agh.to2.dziki.presenter.utils.InputHistory;
import pl.edu.agh.to2.dziki.presenter.utils.TextAutoFiller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;


public class InputController {

    private static final Logger log = LogManager.getLogger(InputController.class);
    private static final int HISTORY_SIZE = 10;
    private InputParser inputParser;
    private TaskCreator taskCreator;
    private TaskExecutor taskExecutor;
    private UndoManager undoManager;
    private InputHistory history;
    private TextAutoFiller autoFiller;
    private FileChooser fileChooser;


    @FXML
    private TextField textField;

    @FXML
    private Canvas drawLayer;

    @FXML
    private Canvas boarLayer;

    @FXML
    private TextArea textArea;

    private Boar boar;


    @FXML
    public void initialize() {
        inputParser = new InputParser();
        taskCreator = new TaskCreator();
        taskExecutor = new TaskExecutor();
        boar = new Boar();
        ViewUpdater viewUpdater = new ViewUpdater(boarLayer, drawLayer, boar);
        undoManager = new UndoManager(taskExecutor, viewUpdater, boar);
        history = new InputHistory(HISTORY_SIZE);
        autoFiller = new TextAutoFiller(Command.getCommandNames());
        fileChooser = new FileChooser();
        setupFileChooser();

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

    private void setupFileChooser() {
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt")
        );
    }

    public void undoButtonHandler(ActionEvent actionEvent) {
        undoManager.undo();
    }

    public void fileButtonHandler() {
        fileChooser.setTitle("Choose LOGO script");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try (Stream<String> file = Files.lines(Paths.get(selectedFile.toURI()))) {
                for (String line : (Iterable<String>) file::iterator) {
                    ValidatedInput validatedInput = inputParser.validate(inputParser.parse(line));
                    List<Task> tasks = taskCreator.createTaskList(boar, validatedInput);
                    taskExecutor.executeTasks(tasks);
                }
            } catch (IOException | IllegalArgumentException | IndexOutOfBoundsException e) {
                printUserError(e);
            }
        }
        textArea.appendText("*************PARSING FINISHED*************\n");
    }

    public void saveButtonHandler() {
        fileChooser.setTitle("Save file");
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            try (FileWriter fileWriter = new FileWriter(selectedFile, true);
                 BufferedReader reader = new BufferedReader(new StringReader(textArea.getText()))
            ) {
                String line = reader.readLine();
                while (line != null) {
                    fileWriter.write(line + System.getProperty("line.separator"));
                    line = reader.readLine();
                }
            } catch (IOException e) {
                printUserError(e);
            }
        }
        textArea.appendText("******************SAVED*******************\n");
    }
}
