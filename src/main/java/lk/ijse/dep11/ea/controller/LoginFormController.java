package lk.ijse.dep11.ea.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane apLoginForm;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Button btnLogin;
    public Hyperlink linkForgotPassword;
    public AnchorPane root;

    public void initialize(){

    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {

        Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("/view/AdminView.fxml")));
        Stage stage = new Stage();
        stage.setScene(mainScene);
        stage.setTitle("Admin");
        stage.show();

        Stage loginStage = (Stage) root.getScene().getWindow();
        loginStage.close();
    }

    public void linkForgotPasswordOnAction(ActionEvent actionEvent) throws IOException {



        Scene  mainScene = new Scene(FXMLLoader.load(getClass().getResource("/view/ForgotPasswordScene.fxml")));
                    Stage stage = new Stage();
                    stage.setScene(mainScene);
                    stage.setTitle("Forgot Password");
                    stage.show();



                Stage loginStage = (Stage) root.getScene().getWindow();
                loginStage.close();

            }
    }

