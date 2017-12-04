package pl.edu.agh.to2.dziki.viewModel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Boar {

    private final static String path = "boar.png";
    private final Canvas canvasLayer;
    private final GraphicsContext graphicsContext;

    public Boar(Canvas layer) {
        this.canvasLayer = layer;
        this.graphicsContext = layer.getGraphicsContext2D();
    }

    public void initialize() {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
        graphicsContext.drawImage(image, 0, 0);
    }

    public void initialize(double x, double y) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
        graphicsContext.drawImage(image, x, y);
    }
}
