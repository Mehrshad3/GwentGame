package view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import model.faction.UnitCard;

public class UnitCardView extends CardView {
    public UnitCardView(Image image, UnitCard card) {
        super(image);

        Label label = new Label();
        card.getPowerProperty().addListener((observableValue, number, t1) -> {
            label.setText(t1.toString());
        });
        this.getChildren().add(label);
    }
}