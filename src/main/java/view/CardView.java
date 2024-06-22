package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CardView extends StackPane {
    protected final ImageView imageView;

    public CardView(Image image) {
        var children = this.getChildren();
        imageView = new ImageView();
        children.add(imageView);
        imageView.setImage(image);
    }

    protected void setFitWidth(double width) {
        imageView.setFitWidth(width);
    }

    protected void setFitHeight(double height) {
        imageView.setFitHeight(height);
    }
}
