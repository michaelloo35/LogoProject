package pl.edu.agh.to2.dziki.presenter;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.model.InputInterpreter;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.parser.InputParser;

import java.util.List;


public class InputController {

    private InputParser inputParser;
    private InputInterpreter inputInterpreter;
    private TaskExecutor executor;
    private ViewUpdater viewUpdater;

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
        viewUpdater = new ViewUpdater(boarLayer, drawLayer);
        boar = new Boar(viewUpdater);
        executor = new TaskExecutor(boar, viewUpdater);
    }

    @FXML
    public void onEnter(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            String message = textField.getText();
            textArea.appendText(message + "\n");
            List<String> validatedInput = inputParser.parse(message);
            List<Task> tasks = inputInterpreter.interpretAndGenerateTasks(validatedInput);
            executor.executeTasks(tasks);
//            TODO commented for testing purposes
            textField.clear();
        }

    }


}
