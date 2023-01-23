package com.example.imageviewernew;

//Evan Geary - 20098723 Computer Forensics and Security.


//General Imports
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class ImageController {
    public ImageView originalImage;
    public MenuButton mainMenuDrop;
    public MenuItem uploadButton;
    public ImageView alteredImage;
    public MenuItem makeGrey;
    public MenuItem makeRed;
    public Label iName;
    public Label iSize;
    public Label iWidth;
    public Label iHeight;
    public MenuItem makeBlue;
    public MenuItem makeGreen;
    public Slider hueSlider;
    public Slider brightness;
    public MenuItem makeOriginal;
    public Label hValue;
    public Label bValue;
    public Button saveButton;
    public Button exitButton;
    public Slider saturation;
    public Label sValue;


    @FXML

    Stage stage;

    ColorAdjust colorAdjust = new ColorAdjust();


    //Upload method
    public void upload() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        if (file == null) {
            return;
        }
        Image image = new Image(file.toURI().toString());
        originalImage.setImage(image);

        File file2 = new File(String.valueOf(image));
        long size = file2.length();
        double sizeInMB = size / (1024.0 * 1024.0);



        //Displaying basic image information
        iName.setText(file.getName());
        iSize.setText("Size of file: " + sizeInMB + " MB");
        iWidth.setText((originalImage.getImage().getWidth() + "PX"));
        iHeight.setText((originalImage.getImage().getHeight() + "PX"));
    }


    //Copying original picture, in case customer wanted to edit original.
    public void turnPictureOriginal() {
        Image image = originalImage.getImage();

        if (image == null) {
            System.out.println("No image selected");
            return;
        }
        alteredImage.setImage(image);
    }

    public void turnPictureGrey() {
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

    public void turnRedScale() {
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


    public void turnGreenScale() {

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

    public void turnBlueScale() {

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


                pixel = (alpha << 24) | (0 << 16) | (0 << 8) | blue;


                redImage.getPixelWriter().setArgb(x, y, pixel);


            }

            alteredImage.setImage(redImage);

        }
    }



    //Sliders for image editing
    public void brightnessSlider() {
        colorAdjust.setBrightness(brightness.getValue());
        alteredImage.setEffect(colorAdjust);
        bValue.setText(String.valueOf(brightness.getValue()));
    }


    //Hue slider using ColorAdjust Class
    public void hueSlider() {
        colorAdjust.setHue(hueSlider.getValue());
        alteredImage.setEffect(colorAdjust);
        hValue.setText(String.valueOf(hueSlider.getValue()));
    }

    public void saturationSlider() {
        colorAdjust.setSaturation(saturation.getValue());
        alteredImage.setEffect(colorAdjust);
        sValue.setText(String.valueOf(saturation.getValue()));
    }


    //Exit button
    public void onExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


    public void onSavePicture() {
//        Image saveImage = originalImage.getImage();
//
//        File outputFile = new File("image.jpg");
//        BufferedImage bImage = SwingFXUtils.fromFXImage(saveImage, null);
//        ImageIO.write(bImage, "jpg", outputFile);
    }


}



