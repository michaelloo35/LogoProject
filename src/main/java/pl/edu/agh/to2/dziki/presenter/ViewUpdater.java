package pl.edu.agh.to2.dziki.presenter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import pl.edu.agh.to2.dziki.model.boar.Boar;

public class ViewUpdater implements Observer<Boar> {


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

    private void updateBoarPosition(Boar boar) {
        clearBoarLayer();

        // setting up rotation
        ImageView iv = new ImageView(image);
        SnapshotParameters params = new SnapshotParameters();
        params.setTransform(new Rotate(boar.getCurrentPosition().getRotation(), image.getHeight() / 2, image.getWidth() / 2));
        params.setViewport(new Rectangle2D(0, 0, image.getWidth() + BOAR_SIZE / 2, image.getHeight()));
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);

        boarLayer.getGraphicsContext2D().drawImage(rotatedImage, boar.getCurrentPosition().getX(), boar.getCurrentPosition().getY());
    }

    private void drawLine(Boar boar) {

        double startX = boar.getPreviousPosition().getX();
        double startY = boar.getPreviousPosition().getY();

        // adding image offset to keep image in tact with drawn lines
        drawLayer.getGraphicsContext2D().strokeLine(
                startX + OFFSET,
                startY + OFFSET,
                boar.getCurrentPosition().getX() + OFFSET,
                boar.getCurrentPosition().getY() + OFFSET);
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
    public void onSubscribe(Disposable d) {

    }

    /**
     * React to boar state-change events
     *
     * @param boar
     */
    @Override
    public void onNext(Boar boar) {

        if (!boar.isHidden())
            updateBoarPosition(boar);
        else
            clearBoarLayer();

        if (!boar.isLift() && !boar.getPreviousPosition().hasSameCoordinatesAs(boar.getCurrentPosition()))
            drawLine(boar);

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
