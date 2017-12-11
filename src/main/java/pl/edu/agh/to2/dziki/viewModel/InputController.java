package pl.edu.agh.to2.dziki.viewModel;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pl.edu.agh.to2.dziki.model.Boar;
import pl.edu.agh.to2.dziki.viewModel.parser.InputParser;


public class InputController {

    private InputParser inputParser;

    @FXML
    private TextField textField;

    @FXML
    private Canvas layer1;

    @FXML
    private Canvas layer2;

    @FXML
    private TextArea textArea;

    private Boar boar;

    @FXML
    public void initialize() {
        boar = new Boar(layer2);
    }

    @FXML
    public void onEnter(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            String message = textField.getText();
            textArea.appendText(message + "\n");
            switch (message) {
                case "start":
                    boar.initialize(100, 100);
                    break;
                case "rotate":
                    boar.rotate(10);
                    break;
                case "rotater":
                    boar.rotate(-10);
                    break;
            }
//            TODO commented for testing purposes
//            textField.clear();
        }

    }


}
