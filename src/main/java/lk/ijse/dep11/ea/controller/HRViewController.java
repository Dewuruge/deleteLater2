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
import lk.ijse.dep11.ea.tm.Employee;

import java.io.IOException;

public class HRViewController {
    public AnchorPane root;
    public TextField txtTerminationContact;
    public TextField txtTerminationName;
    public TextField txtTerminationBranch;
    public TextField txtTerminationID;
    public AnchorPane apTermination;
    public TableView tblTermination;
    public AnchorPane apEmployeeStatus;
    public Pane pDetails2;
    public TextField txtEmployeeStatusID;
    public TextField txtEmployeeStatusBranch;
    public TextField txtEmployeeStatusName;
    public TextField txtEmployeeStatusContact;
    public TextField txtAttendance;
    public TextField txtPercentage;
    public Button btnSearch;
    public JFXButton btnLogout;
    public JFXButton btnEmployeeManagement;
    public JFXButton btnTermination;
    public JFXButton btnEmployeeStatus;
    public Button btnImport;
    public ImageView ivEmployeeManagement;
    public AnchorPane apEmployeeManagementImage;

    public TextField txtEmployeeManagementUsername;
    public ComboBox cbEmployeeManagementRole;

    public TextField txtEmployeeManagementContact;
    public TextField txtEmployeeManagementID;
    public ComboBox cbEmployeeManagementStatus;

    public TextField txtAddress;
    public TextField txtNic;
    public TextField txtEmployeeManagementName;

    public AnchorPane apEmployeeManagement;
    public TextField txtSearch;
    public Label lblDate;
    public Button btnPrintReport;
    public DatePicker dpTo;
    public DatePicker dpFrom;
    public TextField txtWorkdays;
    public TableView<Employee> tblReport;

    public Button btnReason;

    public void initialize(){
        Platform.runLater(()->{
            root.requestFocus();
        });

    }
    public void btnEmployeeManagementSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnEmployeeManagementDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnAddProfilePictureOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteProfilePictureOnAction(ActionEvent actionEvent) {
    }

    public void btnImportOnAction(ActionEvent actionEvent) {
    }

    public void btnHROnAction(ActionEvent actionEvent) throws IOException {
        apEmployeeStatus.setVisible(false);
        apTermination.setVisible(false);
        apEmployeeManagement.setVisible(false);

        if(actionEvent.getSource() == btnEmployeeStatus){
            txtSearch.setVisible(true);
            btnSearch.setVisible(true);

            apEmployeeStatus.setVisible(true);

        }
        if(actionEvent.getSource() == btnTermination){
            txtSearch.setVisible(false);
            btnSearch.setVisible(false);


            apTermination.setVisible(true);

        }
        if(actionEvent.getSource() == btnEmployeeManagement){
            txtSearch.setVisible(false);
            btnSearch.setVisible(false);

            apEmployeeManagement.setVisible(true);

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

    public void btnReasonOnAction(ActionEvent actionEvent) {
    }

    public void btnEmployeeManagementUpdateOnAction(ActionEvent actionEvent) {
    }

    public void dpDOBOnAction(ActionEvent actionEvent) {
    }


}
