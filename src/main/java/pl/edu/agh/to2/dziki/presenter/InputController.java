package pl.edu.agh.to2.dziki.presenter;

import com.sun.deploy.util.StringUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.dziki.model.InputInterpreter;
import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.parser.Command;
import pl.edu.agh.to2.dziki.presenter.parser.InputParser;
import pl.edu.agh.to2.dziki.presenter.utils.InputHistory;
import pl.edu.agh.to2.dziki.presenter.utils.TextAutoFiller;

import java.util.List;

import static java.util.Arrays.asList;


public class InputController {

    private static final Logger log = LogManager.getLogger(InputController.class);
    private static final int HISTORY_SIZE = 10;
    private InputParser inputParser;
    private InputInterpreter inputInterpreter;
    private InputHistory history;
    private TextAutoFiller autoFiller;
    private TaskExecutor executor;

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
        inputInterpreter = new InputInterpreter();
        ViewUpdater viewUpdater = new ViewUpdater(boarLayer, drawLayer);
        boar = new Boar(viewUpdater);
        history = new InputHistory(HISTORY_SIZE);
        autoFiller = new TextAutoFiller(Command.getCommandNames());
        executor = new TaskExecutor(boar, viewUpdater);

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
        textField.setText(StringUtils.join(asList(words), " ").toLowerCase());

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
            List<String> validatedInput = inputParser.parse(message);
            List<Task> tasks = inputInterpreter.interpretAndGenerateTasks(validatedInput);
            executor.executeTasks(tasks);
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

}
