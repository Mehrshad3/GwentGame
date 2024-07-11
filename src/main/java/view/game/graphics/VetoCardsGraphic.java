package view.game.graphics;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.Player;
import model.faction.Card;

import java.awt.*;

public class VetoCardsGraphic {
    /**
     * Starts asking the user which cards they want to redraw.
     *
     * @param stage After dealing cards, the stage should be given as this parameter.
     */
    public Rectangle[] cardViews=new Rectangle[10];
    public Rectangle[] rectangles=new Rectangle[10];
    public boolean[] isselected=new boolean[10];
    public int numberofselectedcards=0;
    public void start(Stage stage, Player player) {

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
    private void Initialize(ObservableList<Card> hand,Pane pane){
        for(int i=0;i<10;i++){
            cardViews[i]=new Rectangle();
            WritableImage image= (WritableImage) CardImageLoader.loadImage(hand.get(i));
            //todo
            pane.getChildren().add(cardViews[i]);
            isselected[i]=false;
        }
    }
    private  void setlocationofcards(){
        //todo
    }
}
