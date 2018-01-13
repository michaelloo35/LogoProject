package pl.edu.agh.to2.dziki.presenter;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import pl.edu.agh.to2.dziki.model.boar.Boar;
import pl.edu.agh.to2.dziki.model.boar.BoarActionData;
import pl.edu.agh.to2.dziki.model.boar.BoarObserver;

public class ViewUpdater implements BoarObserver {


    private static final String BOAR_IMAGE_PATH = "boar.png";
    private static final int BOAR_SIZE = 100;

    /*Since canvas draws an image by its upper left corner we need to subtract little offset in order to
    keep drawn lines in tact with an image*/
    private static final double OFFSET = BOAR_SIZE / 2;
    private final Image image;
    private final Canvas boarLayer;
    private final Canvas drawLayer;

    public ViewUpdater(Canvas boarLayer, Canvas drawLayer, Boar boar) {
        this.boarLayer = boarLayer;
        this.drawLayer = drawLayer;
        this.image = new Image(getClass().getClassLoader().getResourceAsStream(BOAR_IMAGE_PATH), BOAR_SIZE, BOAR_SIZE, false, true);
        boar.subscribe(this);
    }

    private void updateBoarPosition(BoarActionData data) {
        clearBoarLayer();

        // setting up rotation
        ImageView iv = new ImageView(image);
        SnapshotParameters params = new SnapshotParameters();
        params.setTransform(new Rotate(data.getNewPosition().getRotation(), image.getHeight() / 2, image.getWidth() / 2));
        params.setViewport(new Rectangle2D(0, 0, image.getWidth() + BOAR_SIZE / 2, image.getHeight()));
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);

        boarLayer.getGraphicsContext2D().drawImage(rotatedImage, data.getNewPosition().getX(), data.getNewPosition().getY());
    }

    private void drawLine(BoarActionData data) {


        // adding image offset to keep image in tact with drawn lines
        drawLayer.getGraphicsContext2D().strokeLine(
                data.getPreviousPosition().getX() + OFFSET,
                data.getPreviousPosition().getY() + OFFSET,
                data.getNewPosition().getX() + OFFSET,
                data.getNewPosition().getY() + OFFSET);
    }

    private void clearDrawLayer() {
        drawLayer.getGraphicsContext2D().clearRect(0, 0, boarLayer.getWidth(), boarLayer.getHeight());
    }

    private void clearBoarLayer() {
        boarLayer.getGraphicsContext2D().clearRect(0, 0, boarLayer.getWidth(), boarLayer.getHeight());
    }

    public void clearBothLayers() {
        clearDrawLayer();
        clearBoarLayer();
    }

    @Override
    public void onRotate(BoarActionData data) {
        if (!data.isHidden())
            updateBoarPosition(data);
    }

    @Override
    public void onMove(BoarActionData data) {
        if (!data.isLift())
            drawLine(data);

        if (!data.isHidden())
            updateBoarPosition(data);
    }

    @Override
    public void onHide() {
        clearBoarLayer();
    }

    @Override
    public void onShow(BoarActionData data) {
        updateBoarPosition(data);
    }

    @Override
    public void onInitialize(BoarActionData data) {
        updateBoarPosition(data);
    }
}
