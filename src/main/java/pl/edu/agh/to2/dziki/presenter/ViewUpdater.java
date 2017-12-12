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
    private static final int BOAR_SIZE = 100;
    /*Since canvas draws an image by its upper left corner we need to subtract little offset in order to
    keep drawn lines in tact with an image*/
    private static final double OFFSET = BOAR_SIZE / 2;
    private final Image image;
    private final Canvas boarLayer;
    private final Canvas drawLayer;

    public ViewUpdater(Canvas boarLayer, Canvas drawLayer) {
        this.boarLayer = boarLayer;
        this.drawLayer = drawLayer;
        this.image = new Image(getClass().getClassLoader().getResourceAsStream(path), BOAR_SIZE, BOAR_SIZE, false, true);
    }

    public void updateBoarPosition(Boar boar) {
        clearBoarLayer();

        // setting up rotation
        ImageView iv = new ImageView(image);
        SnapshotParameters params = new SnapshotParameters();
        params.setTransform(new Rotate(boar.getPosition().getRotation(), image.getHeight() / 2, image.getWidth() / 2));
        params.setViewport(new Rectangle2D(0, 0, image.getWidth() + BOAR_SIZE / 2, image.getHeight()));
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);

        boarLayer.getGraphicsContext2D().drawImage(rotatedImage, boar.getPosition().getX(), boar.getPosition().getY());
    }

    public void moveAndDraw(Boar boar, double startX, double startY) {

        // adding image offset to keep image in tact with drawn lines
        drawLayer.getGraphicsContext2D().strokeLine(
                startX + OFFSET,
                startY + OFFSET,
                boar.getPosition().getX() + OFFSET,
                boar.getPosition().getY() + OFFSET);
    }

    public void saveCanvas() {
        boarLayer.getGraphicsContext2D().save();
    }

    public void clearBoarLayer() {
        boarLayer.getGraphicsContext2D().clearRect(0, 0, boarLayer.getWidth(), boarLayer.getHeight());
    }


}
