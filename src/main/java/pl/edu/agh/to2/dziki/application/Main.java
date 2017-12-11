package pl.edu.agh.to2.dziki.application;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.to2.dziki.view.View;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new View(primaryStage);
//        view.showStage();
    }

}
