module GWENT {
    requires com.google.gson;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    exports view;
    opens view to javafx.fxml;
    opens model to com.google.gson;
    opens model.faction to com.google.gson;
}