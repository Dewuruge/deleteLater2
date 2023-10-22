package lk.ijse.dep11.ea.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep11.ea.db.EmployeeDataAccess;
import lk.ijse.dep11.ea.tm.Employee;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane apLoginForm;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Button btnLogin;
    public Hyperlink linkForgotPassword;
    public AnchorPane root;

    public void initialize(){

    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException, SQLException {

        String enteredUserName = txtUserName.getText();
        String enteredPassword = txtPassword.getText();

        if (enteredUserName.isBlank() || enteredPassword.isBlank()) {
            if (enteredUserName.isBlank()) {
                txtUserName.requestFocus();
            } else {
                txtPassword.requestFocus();
            }

            return;
        }


        boolean found = EmployeeDataAccess.getAllEmployees().stream()
                .anyMatch(employee -> enteredUserName.equals(employee.getUsername()) && enteredPassword.equals(employee.getPassword()));

        if (found) {
            if(EmployeeDataAccess.getEmployeeRole(enteredUserName,enteredPassword).equals("HR")){
                Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("/view/HRView.fxml")));
                Stage stage = new Stage();
                stage.setScene(mainScene);
                stage.setTitle("HR");
                stage.show();

                Stage loginStage = (Stage) root.getScene().getWindow();
                loginStage.close();


            }
            if(EmployeeDataAccess.getEmployeeRole(enteredUserName,enteredPassword).equals("Admin")){
                Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("/view/AdminView.fxml")));
                Stage stage = new Stage();
                stage.setScene(mainScene);
                stage.setTitle("Admin");
                stage.show();

                Stage loginStage = (Stage) root.getScene().getWindow();
                loginStage.close();


            }
            if(EmployeeDataAccess.getEmployeeRole(enteredUserName,enteredPassword).equals("IT")){
                Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("/view/ITView.fxml")));
                Stage stage = new Stage();
                stage.setScene(mainScene);
                stage.setTitle("IT");
                stage.show();

                Stage loginStage = (Stage) root.getScene().getWindow();
                loginStage.close();


            }
            if(EmployeeDataAccess.getEmployeeRole(enteredUserName,enteredPassword).equals("Employee")){
                Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("/view/EmployeeView.fxml")));
                Stage stage = new Stage();
                stage.setScene(mainScene);
                stage.setTitle("Employee");
                stage.show();

                Stage loginStage = (Stage) root.getScene().getWindow();
                loginStage.close();


            }
            // Valid user, open the Account

        }
        else {
            // Invalid username or password
            new Alert(Alert.AlertType.ERROR, "Invalid User Name or Password").show();


        }
//        Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("/view/AdminView.fxml")));
//        Stage stage = new Stage();
//        stage.setScene(mainScene);
//        stage.setTitle("Admin");
//        stage.show();
//
//        Stage loginStage = (Stage) root.getScene().getWindow();
//        loginStage.close();
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

