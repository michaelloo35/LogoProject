package pl.edu.agh.to2.dziki.presenter;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.to2.dziki.model.boar.BoarActionData;
import pl.edu.agh.to2.dziki.model.boar.BoarObserver;
import pl.edu.agh.to2.dziki.model.boar.ObservableBoar;
import pl.edu.agh.to2.dziki.model.task.Task;
import pl.edu.agh.to2.dziki.presenter.undo.ObservableTaskExecutor;
import pl.edu.agh.to2.dziki.presenter.undo.TaskExecutorObserver;
import pl.edu.agh.to2.dziki.utils.UnmodifiableList;

import java.util.EmptyStackException;

public class ViewUpdater implements BoarObserver, TaskExecutorObserver {

    private static final Logger log = LogManager.getLogger(ViewUpdater.class);
    private static final String BOAR_IMAGE_PATH = "boar.png";
    private static final int BOAR_SIZE = 100;

    /*Since canvas draws an image by its upper left corner we need to subtract little offset in order to
    keep drawn lines in tact with an image*/
    private static final double BOAR_TEXTURE_OFFSET = BOAR_SIZE / 2;
    private final Image image;
    private final Canvas boarLayer;
    private final Canvas drawLayer;

    public ViewUpdater(Canvas boarLayer, Canvas drawLayer, ObservableBoar boar, ObservableTaskExecutor taskExecutor) {
        this.boarLayer = boarLayer;
        this.drawLayer = drawLayer;
        this.image = new Image(getClass().getClassLoader().getResourceAsStream(BOAR_IMAGE_PATH), BOAR_SIZE, BOAR_SIZE, false, true);
        boar.subscribe(this);
        taskExecutor.subscribe(this);
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
                data.getPreviousPosition().getX() + BOAR_TEXTURE_OFFSET,
                data.getPreviousPosition().getY() + BOAR_TEXTURE_OFFSET,
                data.getNewPosition().getX() + BOAR_TEXTURE_OFFSET,
                data.getNewPosition().getY() + BOAR_TEXTURE_OFFSET);
    }

    private void clearDrawLayer() {
        drawLayer.getGraphicsContext2D().clearRect(0, 0, boarLayer.getWidth(), boarLayer.getHeight());
    }

    private void clearBoarLayer() {
        boarLayer.getGraphicsContext2D().clearRect(0, 0, boarLayer.getWidth(), boarLayer.getHeight());
    }

    private void clearBothLayers() {
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

    @Override
    public void onClear() {
        clearDrawLayer();
    }

    @Override
    public void onRectangle(BoarActionData data, double width, double height) {
        GraphicsContext gc = drawLayer.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(
                data.getNewPosition().getX() + BOAR_TEXTURE_OFFSET,
                data.getNewPosition().getY() + BOAR_TEXTURE_OFFSET, width, height);
    }

    @Override
    public void onOval(BoarActionData data, double width, double height) {
        GraphicsContext gc = drawLayer.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillOval(
                data.getNewPosition().getX() + BOAR_TEXTURE_OFFSET,
                data.getNewPosition().getY() + BOAR_TEXTURE_OFFSET, width, height);
    }

    @Override
    public void onUndo(UnmodifiableList<Task> tasksHistory) {
        clearBothLayers();
        try {
            tasksHistory.forEach(Task::execute);
            log.debug("Undo completed");

        } catch (EmptyStackException e) {
            log.debug("Can't pop stack any task. History is empty");
        }

    }
}
