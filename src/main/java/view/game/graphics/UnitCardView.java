package view.game.graphics;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.faction.UnitCard;

class UnitCardView extends CardView {
    public UnitCardView(UnitCard card) {
        super(card);
        final int PADDING_FROM_LEFT = 5;
        this.setAlignment(Pos.TOP_LEFT);

        Circle textBackground = new Circle(7.5, Color.WHITE);
        this.getChildren().add(textBackground);
        StackPane.setMargin(textBackground, new Insets(0, 0, 0, PADDING_FROM_LEFT));

        IntegerProperty powerProperty = card.getPowerProperty();
        Text text = new Text(Integer.toString(powerProperty.getValue())); // Text is blacker than label, so it easier to read.
        text.setFont(new Font("Arial", 15));
        powerProperty.addListener((observableValue, number, t1) -> {
            text.setText(t1.toString());
            StackPane.setMargin(text, new Insets(0, 0, 0,
                    PADDING_FROM_LEFT + textBackground.getRadius())); // This isn't near the answer at all!
        });
        text.setWrappingWidth(30);
        this.getChildren().add(text);
        StackPane.setMargin(text, new Insets(0, 0, 0, 5)); // Top, Right, Bottom, Left
    }
}