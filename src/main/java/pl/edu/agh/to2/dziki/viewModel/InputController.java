package pl.edu.agh.to2.dziki.viewModel;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pl.edu.agh.to2.dziki.model.InputParser;


public class InputController {

    private InputParser inputParser;

    @FXML
    private TextField textField;

    @FXML
    private Canvas canvas;

    @FXML
    private TextArea textArea;

    private GraphicsContext gc;

    @FXML
    public void onEnter(KeyEvent ke) {
        // TODO can controller have a state ? where do we initialize it?
        gc = canvas.getGraphicsContext2D();
        Boar boar = new Boar(canvas, gc);
        if (ke.getCode().equals(KeyCode.ENTER)) {
            String message = textField.getText();
            textArea.appendText(message + "\n");
            textField.clear();
            boar.initialize();
        }
    }


}
