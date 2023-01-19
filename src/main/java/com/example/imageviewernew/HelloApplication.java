package com.example.imageviewernew;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("imageViewerMainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Image Viewer");
        stage.setScene(scene);
        stage.getIcons().add(new Image("https://www.computerhope.com/jargon/p/paint-logo.png"));
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

}