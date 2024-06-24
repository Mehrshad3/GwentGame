module GWENT {
    requires com.google.gson;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    exports view;
    exports model.faction;
    opens view to javafx.fxml;
    opens model to com.google.gson;
    opens model.faction to com.google.gson;
    opens enums.card to com.google.gson;
    opens enums.card.ability to com.google.gson;
}