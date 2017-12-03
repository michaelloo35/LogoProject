package pl.edu.agh.to2.dziki.viewModel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Boar {

    private final static String path = "boar.png";
    private final Canvas canvas;
    private final GraphicsContext graphicsContext;

    public Boar(Canvas canvas, GraphicsContext graphicsContext) {
        this.canvas = canvas;
        this.graphicsContext = graphicsContext;
    }

    public void initialize() {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
        graphicsContext.drawImage(image, 0, 0);
    }
}
