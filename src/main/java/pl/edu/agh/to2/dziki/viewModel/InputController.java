package pl.edu.agh.to2.dziki.viewModel;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pl.edu.agh.to2.dziki.viewModel.parser.InputParser;


public class InputController {

    private InputParser inputParser;

    @FXML
    private TextField textField;

    @FXML
    private Canvas canvas;

    @FXML
    private TextArea textArea;

    private GraphicsContext graphicsContext;
    private Boar boar;

    @FXML
    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
        boar = new Boar(canvas, graphicsContext);
    }

    @FXML
    public void onEnter(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            String message = textField.getText();
            textArea.appendText(message + "\n");
            textField.clear();
            boar.initialize();
        }
    }


}
