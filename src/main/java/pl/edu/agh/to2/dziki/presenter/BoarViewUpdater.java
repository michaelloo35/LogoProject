package pl.edu.agh.to2.dziki.presenter;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import pl.edu.agh.to2.dziki.model.Boar;

public class BoarViewUpdater {


    private static final String path = "boar.png";
    private final Image image;
    private final Canvas canvasLayer;
    private final GraphicsContext graphicsContext;

    public BoarViewUpdater(Canvas layer) {
        this.canvasLayer = layer;
        this.graphicsContext = layer.getGraphicsContext2D();
        this.image = new Image(getClass().getClassLoader().getResourceAsStream(path), 100, 100, false, true);
    }

    public void initialize(Boar boar) {
        clearCanvas();
        graphicsContext.drawImage(image, boar.getPosition().getX(), boar.getPosition().getY());
    }

    public void updateRotation(Boar boar) {
        clearCanvas();

        ImageView iv = new ImageView(image);
        SnapshotParameters params = new SnapshotParameters();
        params.setTransform(new Rotate(boar.getPosition().getRotation(), image.getHeight() / 2, image.getWidth() / 2));
        params.setViewport(new Rectangle2D(0, 0, image.getWidth(), image.getHeight()));
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);

        graphicsContext.drawImage(rotatedImage, boar.getPosition().getX(), boar.getPosition().getY());
    }

    public void saveCanvas() {
        graphicsContext.save();
    }

    public void clearCanvas() {
        graphicsContext.clearRect(0, 0, canvasLayer.getWidth(), canvasLayer.getHeight());
    }

}
