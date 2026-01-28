module org.noobtools.rpggame {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.noobtools.rpggame to javafx.fxml;
    exports org.noobtools.rpggame;
}