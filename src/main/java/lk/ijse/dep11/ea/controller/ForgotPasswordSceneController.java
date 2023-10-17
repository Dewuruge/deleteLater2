package lk.ijse.dep11.ea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgotPasswordSceneController {
    public TextField txtID;
    public TextField txtNic;
    public Button btnSend;
    public AnchorPane root;

    public void btnSendOnAction(ActionEvent actionEvent) throws IOException {
        Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml")));
        Stage stage = new Stage();
        stage.setScene(mainScene);
        stage.setTitle("Login");
        stage.show();

        Stage loginStage = (Stage) root.getScene().getWindow();
        loginStage.close();

    }
}
