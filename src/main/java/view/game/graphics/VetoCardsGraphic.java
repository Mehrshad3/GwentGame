package view.game.graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class VetoCardsGraphic extends Application {
    /**
     * Starts asking the user which cards they want to redraw.
     *
     * @param stage After dealing cards, the stage should be given as this parameter.
     */
    @Override
    public void start(Stage stage) {
        WritableImage backGroundImage = stage.getScene().snapshot(null);
        Pane pane = new Pane();
        setBackground(pane, backGroundImage);
        Scene newScene = new Scene(pane);
        stage.setScene(newScene);
        stage.show();
    }

    private static void setBackground(Pane pane, WritableImage backGroundImage) {
        pane.setBackground(Background.fill(Color.BLACK));
        pane.setBackground(Background.fill(new ImagePattern(backGroundImage)));
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setInput(new GaussianBlur(70));
        colorAdjust.setBrightness(-0.2);
        pane.setEffect(colorAdjust);
    }
}
