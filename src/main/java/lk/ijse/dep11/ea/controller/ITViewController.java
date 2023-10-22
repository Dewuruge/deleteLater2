package lk.ijse.dep11.ea.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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

public class ITViewController {
    public AnchorPane root;
    public JFXButton btnITStatus;
    public JFXButton btnITUpdate;
    public Button btnUpdate;
    public JFXButton btnLogout;
    public JFXButton btnRequest;
    public Button btnSearch;
    public AnchorPane apRequest;
    public TableView tblRequest;
    public Button btnImport;
    public ImageView ivEmployeeManagement;
    public AnchorPane apEmployeeManagementImage;
    public TextField txtUsername;
    public TextField txtRole;
    public TextField txtITUpdateContact;
    public TextField txtITUpdatetID;
    public TextField txtITStatus;
    public TextField cbTUpdateBranch;
    public DatePicker dpDOB;
    public TextField txtAddress;
    public TextField txtNic;
    public TextField txtITUpdateName;
    public AnchorPane apUpdate;
    public Label lblDate;
    public Button btnPrintReport;
    public DatePicker dpTo;
    public DatePicker dpFrom;
    public TextField txtPercentage;
    public TextField txtAttendance;
    public TextField txtWorkdays;
    public TableView tblReport;
    public TextField txtEmployeeStatusContact;
    public TextField txtEmployeeStatusName;
    public TextField txtEmployeeStatusBranch;
    public TextField txtEmployeeStatusID;
    public Pane pDetails2;
    public AnchorPane apStatus;
    public JFXTextField txtSearch;

    public void initialize(){
        Platform.runLater(()->{
            root.requestFocus();
        });

    }


    public void btnImportOnAction(ActionEvent actionEvent) {
    }

    public void btnITOnAction(ActionEvent actionEvent) throws IOException {
        apStatus.setVisible(false);
        apUpdate.setVisible(false);
        apRequest.setVisible(false);

        if(actionEvent.getSource() == btnITStatus){
            txtSearch.setVisible(true);
            btnSearch.setVisible(true);

            apStatus.setVisible(true);

        }
        if(actionEvent.getSource() == btnITUpdate){
            txtSearch.setVisible(false);
            btnSearch.setVisible(false);


            apUpdate.setVisible(true);

        }
        if(actionEvent.getSource() == btnRequest){
            txtSearch.setVisible(true);
            btnSearch.setVisible(true);

            apRequest.setVisible(true);

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

    public void btnUpdate(ActionEvent actionEvent) {
    }

    public void dpDOBOnAction(ActionEvent actionEvent) {
    }
}
