module GWENT {
    requires com.google.gson;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    requires javafx.base;
    exports view;
    exports model.faction;
    opens view to javafx.fxml;
    opens model to com.google.gson;
    opens model.faction to com.google.gson;
    opens enums.card to com.google.gson;
    opens enums.card.ability to com.google.gson;
    opens model.typeadapters to com.google.gson;
    exports view.gamegraphics;
    opens view.gamegraphics to javafx.fxml;
}