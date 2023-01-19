module com.example.imageviewernew {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.imageviewernew to javafx.fxml;
    exports com.example.imageviewernew;
}