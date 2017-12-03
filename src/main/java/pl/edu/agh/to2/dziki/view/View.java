package pl.edu.agh.to2.dziki.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class View {

    private static final String TITLE = "Wildschwein zeichnen";
    public static final String FXML_FILE_NAME = "test.fxml";
    private Stage primaryStage;

    public View(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showStage() throws IOException {
        setupStage();
        primaryStage.show();
    }

    private void setupStage() throws IOException {
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(getRoot()));
    }

    private Parent getRoot() throws java.io.IOException {
        return FXMLLoader.load(getClass().getResource(FXML_FILE_NAME));
    }
}
