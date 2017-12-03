package pl.edu.agh.to2.dziki.viewModel;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class InputController {


    @FXML
    private TextField textField;

    @FXML
    private Canvas canvas;

    @FXML
    private TextArea textArea;

    private GraphicsContext gc;

    @FXML
    public void onEnter(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            String message = textField.getText();
            textArea.appendText(message + "\n");
            textField.clear();
            gc = canvas.getGraphicsContext2D();
            Image image = new Image(getClass().getClassLoader().getResourceAsStream("boar.png"));
            gc.drawImage(image,0,0);
        }
    }


}
