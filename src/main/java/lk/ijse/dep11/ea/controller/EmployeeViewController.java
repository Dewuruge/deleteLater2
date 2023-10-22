package lk.ijse.dep11.ea.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeViewController {
    public Button btnSearch;
    public Button btnEmployeeManagementUpdate;
    public AnchorPane apStatus;
    public Pane pDetails2;
    public TextField txtEmployeeStatusID;
    public TextField txtEmployeeStatusBranch;
    public TextField txtEmployeeStatusName;
    public TextField txtEmployeeStatusContact;
    public TableView tblReport;
    public TextField txtWorkdays;
    public TextField txtAttendance;
    public TextField txtPercentage;
    public DatePicker dpFrom;
    public DatePicker dpTo;
    public Button btnPrintReport;
    public AnchorPane apUpdate;
    public Label lblDate;
    public TextField txtEmployeeManagementName;
    public TextField txtNic;
    public TextField txtAddress;
    public DatePicker dpDOB;
    public ComboBox<String> cbEmployeeManagementStatus;
    public TextField txtEmployeeManagementID;
    public TextField txtEmployeeManagementContact;
    public ComboBox<String> cbEmployeeManagementRole;
    public TextField txtEmployeeManagementUsername;
    public AnchorPane apEmployeeManagementImage;
    public ImageView ivEmployeeManagement;
    public Button btnImport;
    public JFXButton btnStatus;
    public JFXButton btnUpdate;
    public JFXButton btnLogout;
    public AnchorPane root;

    public void initialize(){
        Platform.runLater(()->{
            root.requestFocus();
        });

    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {


        apUpdate.setVisible(false);
        apStatus.setVisible(false);

        if(actionEvent.getSource() == btnStatus){

            apStatus.setVisible(true);

        }
        if(actionEvent.getSource() == btnUpdate){
       

            apUpdate.setVisible(true);

        }

        if(actionEvent.getSource() == btnLogout){
            Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml")));
            Stage stage = new Stage();
            stage.setScene(mainScene);
            stage.setTitle("Login");
            stage.show();

            Stage loginStage = (Stage) root.getScene().getWindow();
            loginStage.close();

        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnEmployeeManagementUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnImportOnAction(ActionEvent actionEvent) {
    }

    public void dpDOBOnAction(ActionEvent actionEvent) {
    }
}
