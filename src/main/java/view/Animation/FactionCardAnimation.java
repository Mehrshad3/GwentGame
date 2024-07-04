package view.Animation;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class FactionCardAnimation extends Transition {
    private ImageView imageView;
    private boolean makeSmall;
    private double height;
    private double width;
    public FactionCardAnimation(ImageView imageView , double height , double width , boolean makeSmall){
        this.imageView = imageView;
        this.height = height;
        this.width = width;
        this.makeSmall = makeSmall;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(200));
    }
    @Override
    protected void interpolate(double v) {
        int heightSize = 100;
        int widthSize = 60;
        if(makeSmall && heightSize > 0){
            heightSize *= -1;
            widthSize *= -1;
        }
        imageView.setFitHeight(height + heightSize*v);
        imageView.setFitWidth(width + widthSize*v);
    }
}
