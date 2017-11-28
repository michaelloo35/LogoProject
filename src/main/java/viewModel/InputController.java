package viewModel;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class InputController {

    @FXML
    private TextField textField;

    @FXML
    public void onEnter(KeyEvent ke){
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            textField.clear();
        }
    }
}
