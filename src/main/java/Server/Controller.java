package Server;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Scanner;

public class Controller {
    public void commandLine(){
        Scanner scanner = new Scanner(System.in);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1),actionEvent -> System.out.println(scanner.nextLine())));
        timeline.setCycleCount(-1);
        timeline.play();
    }
}
