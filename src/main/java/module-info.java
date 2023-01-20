module com.example.imageviewernew {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;
    requires java.desktop;


    opens com.example.imageviewernew to javafx.fxml;
    exports com.example.imageviewernew;
}