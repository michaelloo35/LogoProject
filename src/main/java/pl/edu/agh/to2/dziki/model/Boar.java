package pl.edu.agh.to2.dziki.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import pl.edu.agh.to2.dziki.viewModel.Position;

public class Boar {

    private static final Logger log = LogManager.getLogger(Boar.class);
    private final static String path = "boar.png";
    private static final String POSITION_MESSAGE = "Boar position set to x:%.2f y:%.2f %n";
    private final Image image;
    private final Canvas canvasLayer;
    private final GraphicsContext graphicsContext;
    //TODO this has to be position of center of the boar not the upper left corner or we have to keep also position of center
    private final Position position;

    public Boar(Canvas layer) {
        this.canvasLayer = layer;
        this.graphicsContext = layer.getGraphicsContext2D();
        this.position = new Position();
        this.image = new Image(getClass().getClassLoader().getResourceAsStream(path), 100, 100, false, true);

    }

    public void initialize() {
        this.position.setX(0);
        this.position.setY(0);
        log.info(String.format(POSITION_MESSAGE,0.0,0.0));
        graphicsContext.drawImage(image, position.getX(), position.getY());
    }

    public void initialize(double x, double y) {
        this.position.setX(x);
        this.position.setY(y);
        log.info(String.format(POSITION_MESSAGE,x,y));

        graphicsContext.drawImage(image, position.getX(), position.getY());
    }

    public void rotate(double degrees) {
        position.rotate(degrees);
        ImageView iv = new ImageView(image);
        SnapshotParameters params = new SnapshotParameters();
        params.setTransform(new Rotate(position.getRotation(), image.getHeight() / 2, image.getWidth() / 2));
        params.setViewport(new Rectangle2D(0, 0, image.getWidth(), image.getHeight()));
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);

        graphicsContext.clearRect(0, 0, canvasLayer.getWidth(), canvasLayer.getHeight());
        graphicsContext.drawImage(rotatedImage, position.getX(), position.getY());
        update();
    }

    private void update() {
        graphicsContext.save();
    }
}
