package pl.edu.agh.to2.dziki.presenter;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import pl.edu.agh.to2.dziki.model.Boar;

public class ViewUpdater {


    private static final String path = "boar.png";
    private final Image image;
    private final Canvas boarLayer;
    private final Canvas drawLayer;

    public ViewUpdater(Canvas boarLayer, Canvas drawLayer) {
        this.boarLayer = boarLayer;
        this.drawLayer = drawLayer;
        this.image = new Image(getClass().getClassLoader().getResourceAsStream(path), 100, 100, false, true);
    }

    public void updateBoarPosition(Boar boar) {
        clearBoarLayer();

        ImageView iv = new ImageView(image);
        SnapshotParameters params = new SnapshotParameters();
        params.setTransform(new Rotate(boar.getPosition().getRotation(), image.getHeight() / 2, image.getWidth() / 2));
        params.setViewport(new Rectangle2D(0, 0, image.getWidth(), image.getHeight()));
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);

        boarLayer.getGraphicsContext2D().drawImage(rotatedImage, boar.getPosition().getX(), boar.getPosition().getY());
    }

    public void moveAndDraw(Boar boar, double startX, double startY) {
        drawLayer.getGraphicsContext2D().strokeLine(startX, startY, boar.getPosition().getX(), boar.getPosition().getY());
    }

    public void saveCanvas() {
        boarLayer.getGraphicsContext2D().save();
    }

    public void clearBoarLayer() {
        boarLayer.getGraphicsContext2D().clearRect(0, 0, boarLayer.getWidth(), boarLayer.getHeight());
    }

}
