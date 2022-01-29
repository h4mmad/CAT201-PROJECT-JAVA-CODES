package com.hammad.jfx1;

import com.mysql.jdbc.Driver;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginScreen.fxml"));
        String css = Objects.requireNonNull(this.getClass().getResource("style.css")).toExternalForm();

        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add(css);

        Image logo = new Image("C:\\Users\\Hammad\\Desktop\\JAVAFX\\jfx-1\\src\\main\\resources\\com\\hammad\\jfx1\\car next-logos_black.png");
        stage.getIcons().add(logo);

        stage.setTitle("CAR NEXT");
        stage.setResizable(false);


        stage.setScene(scene);
        stage.show();


    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        launch();



    }





}