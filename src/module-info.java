module myjfx {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    opens controller_and_fxml to javafx.graphics, javafx.fxml;
    opens objects to javafx.base;
    opens clients to javafx.fxml, javafx.graphics;
}