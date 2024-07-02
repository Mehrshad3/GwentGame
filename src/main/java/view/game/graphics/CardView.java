package view.game.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.faction.Card;

class CardView extends StackPane {
    protected final Card card;
    protected final ImageView imageView;

    public CardView(Card card) {
        this.card = card;
        Image image = CardImageLoader.loadImage(card);
        this.imageView = new ImageView();
        imageView.setImage(image);
        this.getChildren().add(imageView);
    }

    protected final void setFitWidth(double width) {
        imageView.setFitWidth(width);
    }

    protected final void setFitHeight(double height) {
        imageView.setFitHeight(height);
    }
}
