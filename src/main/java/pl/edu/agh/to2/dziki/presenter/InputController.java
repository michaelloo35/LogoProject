package pl.edu.agh.to2.dziki.presenter;

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
import pl.edu.agh.to2.dziki.presenter.parser.InputParser;

import java.util.List;


public class InputController {

    private static final Logger log = LogManager.getLogger(InputController.class);
    private static final int HISTORY_SIZE = 10;
    private InputParser inputParser;
    private InputInterpreter inputInterpreter;
    private TaskExecutor executor;
    private InputHistory history;

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
        executor = new TaskExecutor(boar, viewUpdater);
        history = new InputHistory(HISTORY_SIZE);

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
        }

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
        } catch (IllegalArgumentException e) {
            textArea.appendText("******************ERROR******************\n");
            textArea.appendText(e.getMessage() + "\n");
            textArea.appendText("******************ERROR******************\n");
        }
        textField.clear();
    }

}
