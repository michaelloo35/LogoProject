package pl.edu.agh.to2.dziki.presenter.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SnapshotManager {
    private final Canvas drawLayer;

    public SnapshotManager(Canvas drawLayer) {
        this.drawLayer = drawLayer;
    }

    public void saveAsPng() throws IOException {
        WritableImage image = new WritableImage(ceil(drawLayer.getWidth()), ceil(drawLayer.getHeight()));
        drawLayer.snapshot(new SnapshotParameters(), image);

        // choose location for the image
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save snapshot");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"));
        File file = fileChooser.showSaveDialog(null);

        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);

    }

    private int ceil(double x) {
        return (int) Math.ceil(x);
    }
}
