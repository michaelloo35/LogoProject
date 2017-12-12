package pl.edu.agh.to2.dziki.presenter;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.presenter.parser.InputParser;


public class InputController {

    private InputParser inputParser;

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
        boar = new Boar(new ViewUpdater(boarLayer, drawLayer));
    }

    @FXML
    public void onEnter(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            String message = textField.getText();
            textArea.appendText(message + "\n");
            switch (message) {
                case "start":
                    boar.initialize();
                    break;
                case "r":
                    boar.rotate(10);
                    break;
                case "rr":
                    boar.rotate(-10);
                    break;
                case "f":
                    boar.moveForward(10);
                    break;
                case "b":
                    boar.moveBackward(10);
                    break;
                case "h":
                    boar.hide();
                    break;

            }
//            TODO commented for testing purposes
//            textField.clear();
        }

    }


}
