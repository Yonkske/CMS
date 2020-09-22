module org.dhbw {

    requires javafx.controls;
    requires javafx.fxml;

    opens org.dhbw to javafx.fxml;
    exports org.dhbw;
}