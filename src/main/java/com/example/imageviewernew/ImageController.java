package com.example.imageviewernew;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ImageController {
    public ImageView originalImage;
    public ImageView newImage;
    public MenuButton mainMenuDrop;
    public MenuItem uploadButton;
    public ImageView view;
    public ImageView alteredImage;
    public MenuItem makeGrey;
    public MenuItem makeRed;
    public Label iName;
    public Label iSize;
    public Label iWidth;
    public Label iHeight;
    public MenuItem makeBlue;
    public MenuItem makeGreen;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML

    Stage stage;

    public void upload(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file == null) {
            return;
        }
        Image image = new Image(file.toURI().toString());
        originalImage.setImage(image);

        iName.setText(file.getName());
        iSize.setText(file.length() + "KB");
        iWidth.setText(String.valueOf(originalImage.getImage().getWidth() + "PX"));
        iHeight.setText(String.valueOf(originalImage.getImage().getHeight() + "PX"));
    }

    public void turnPictureGrey(ActionEvent actionEvent) {
        Image image = originalImage.getImage();

        if (image == null) {
            System.out.println("No image selected");
            return;
        }
        PixelReader pixelReader = image.getPixelReader();

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        WritableImage grayImage = new WritableImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = pixelReader.getArgb(x, y);

                int alpha = ((pixel >> 24) & 0xff);
                int red = ((pixel >> 16) & 0xff);
                int green = ((pixel >> 8) & 0xff);
                int blue = (pixel & 0xff);

                int grayLevel = (int) (0.2162 * red + 0.7152 * green + 0.0722 * blue);
                int grey = (alpha << 24) + (grayLevel << 16) + (grayLevel << 8) + grayLevel;

                grayImage.getPixelWriter().setArgb(x, y, grey);
            }

            alteredImage.setImage(grayImage);

        }
    }

    public void turnRedScale(ActionEvent actionEvent) {
        Image image = originalImage.getImage();

        if (image == null) {
            System.out.println("No image selected");
            return;
        }

        PixelReader pixelReader = image.getPixelReader();

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        WritableImage redImage = new WritableImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = pixelReader.getArgb(x, y);

                int alpha = ((pixel >> 24) & 0xff);
                int red = ((pixel >> 16) & 0xff);

                pixel = (alpha << 24) | (red << 16) | (0 << 8);
                redImage.getPixelWriter().setArgb(x, y, pixel);
            }

            alteredImage.setImage(redImage);

        }
    }

    public void turnBlueScale(ActionEvent actionEvent) {

        Image image = originalImage.getImage();

        if (image == null) {
            System.out.println("No image selected");
            return;
        }

        PixelReader pixelReader = image.getPixelReader();

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        WritableImage redImage = new WritableImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = pixelReader.getArgb(x, y);

                int alpha = ((pixel >> 24) & 0xff);
                int blue = (pixel & 0xff);


                pixel = (alpha << 24) | (0 << 16) | (0 << 8)| blue;


                redImage.getPixelWriter().setArgb(x, y, pixel);


            }

            alteredImage.setImage(redImage);

        }
    }

    public void turnGreenScale(ActionEvent actionEvent) {

        Image image = originalImage.getImage();

        if (image == null) {
            System.out.println("No image selected");
            return;
        }

        PixelReader pixelReader = image.getPixelReader();

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        WritableImage redImage = new WritableImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = pixelReader.getArgb(x, y);

                int alpha = ((pixel >> 24) & 0xff);
                int green = (pixel & 0xff);


                pixel = (alpha << 24) | (0 << 16) | (green << 8);


                redImage.getPixelWriter().setArgb(x, y, pixel);


            }

            alteredImage.setImage(redImage);

        }
    }
}

