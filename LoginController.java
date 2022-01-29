package com.hammad.jfx1;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ImageView loginBtn;

    @FXML
    private Label incorrect;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    public void login(MouseEvent event) throws IOException, NullPointerException, InterruptedException {
        if (username.getText().equals("admin") && password.getText().equals("password")){

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();




        }
        else{

            //incorrect.setText("The username or password is incorrect");

            RotateTransition rotate = new RotateTransition();
            rotate.setNode(loginBtn);
            rotate.setDuration(Duration.millis(500));
            rotate.setByAngle(360);
            rotate.play();

            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Login Error");
            error.setHeaderText("Username/Password is incorrect");
            error.show();





        }



    }


}
