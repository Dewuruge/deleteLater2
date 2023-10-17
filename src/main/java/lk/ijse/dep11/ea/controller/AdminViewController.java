package lk.ijse.dep11.ea.controller;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;



import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.dep11.ea.db.EmployeeDataAccess;
import lk.ijse.dep11.ea.tm.Employee;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AdminViewController {

    public AnchorPane apTermination;
    public AnchorPane apEmployeeStatus;
    public Button btnEmployeeStatus;
    public Button btnTermination;
    public Button btnEmployeeManagement;
    public TextField txtSearch;
    public Button btnSearch;
    public AnchorPane apEmployeeManagement;
    public AnchorPane root;
    public TextField txtTerminationID;
    public TextField txtTerminationBranch;
    public TextField txtTerminationName;
    public TextField txtTerminationContact;
    public TableView tblTermination;
    public TextField txtEmployeeStatusID;
    public TextField txtEmployeeStatusBranch;
    public TextField txtEmployeeStatusName;
    public TextField txtEmployeeStatusContact;
    public TableView tblReport;
    public TextField txtAttendance;
    public TextField txtWorkdays;
    public TextField txtPercentage;
    public DatePicker dpFrom;
    public DatePicker dpTo;
    public Button btnPrintReport;
    public Label lblDate;
    public TableView<Employee> tblEmployeeDetails;
    public TextField txtEmployeeManagementName;
    public TextField txtNic;
    public TextField txtAddress;
    public Button btnEmployeeManagementSave;
    public TextField txtEmployeeManagementBranch;
    public TextField txtEmployeeManagementID;
    public TextField txtEmployeeManagementContact;
    public Button btnEmployeeManagementDelete;
    public Circle shpEmployeeManagementImage;
    public Button btnAddProfilePicture;
    public Button btnDeleteProfilePicture;
    public Button btnExit;

    public ComboBox <String>cbEmployeeManagementStatus;
    public ComboBox <String>cbEmployeeManagementRole;
    public TextField txtDOB;
    public TextField txtEmployeeManagementUsername;
    private final static String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public Pane pDetails2;
    private final String [] role = {"HR","Regular","Admin","IT"};
    private final  String [] status = {"Temporary","Intern","Permanent","Probation"};
    public ImageView ivEmployeeManagement;
    public ImageView ivEmployeeStatus;
    public ImageView ivTermination;
    public Button btnImport;
    public AnchorPane apEmployeeManagementImage;
    public String selectedStatus;
    public String selectedRole;
    byte[] imageBytes;



    public void initialize() throws SQLException {
        tblEmployeeDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblEmployeeDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmployeeDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblEmployeeDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblEmployeeDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("DOB"));
        tblEmployeeDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("branch"));
        tblEmployeeDetails.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("status"));
        tblEmployeeDetails.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("contact"));
//        tblEmployeeDetails.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("password"));
//        tblEmployeeDetails.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("role"));
//        tblEmployeeDetails.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("username"));
        tblEmployeeDetails.getSelectionModel().selectedItemProperty().addListener((ov, prev, cur) -> {
            if(cur==null){

                btnEmployeeManagementDelete.setDisable(true);
            }
            if(cur != null){
                txtAddress.setText(cur.getAddress());
                txtDOB.setText(cur.getDOB().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                txtEmployeeManagementID.setText(cur.getId());
                txtEmployeeManagementBranch.setText(cur.getBranch());
                txtEmployeeManagementContact.setText(cur.getContact());
                txtNic.setText(cur.getNic());
                txtEmployeeManagementName.setText(cur.getName());
                cbEmployeeManagementRole.setValue(cur.getRole());
                cbEmployeeManagementStatus.setValue(cur.getStatus());
                txtEmployeeManagementUsername.setText(cur.getUsername());

            }
        });

        cbEmployeeManagementStatus.getSelectionModel().selectedItemProperty().addListener((ov, prev, cur) ->{
        selectedStatus = cbEmployeeManagementStatus.getSelectionModel().getSelectedItem();;

        });
        cbEmployeeManagementRole.getSelectionModel().selectedItemProperty().addListener((ov, prev, cur) ->{
            selectedRole = cbEmployeeManagementRole.getSelectionModel().getSelectedItem();;

        });

//        txtEmployeeManagementID.setDisable(true);

        tblEmployeeDetails.getItems().addAll(EmployeeDataAccess.getAllCustomers());

        cbEmployeeManagementRole.getItems().addAll(role);
        cbEmployeeManagementStatus.getItems().addAll(status);

        Platform.runLater(() ->{
            root.requestFocus();
        });



    }
    public void btnAdminOnAction(ActionEvent actionEvent) throws IOException {
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
        if(actionEvent.getSource() == btnExit){
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

    public void btnEmployeeManagementSaveOnAction(ActionEvent actionEvent) {
        lblDate.setText(date);
    }

    public void btnEmployeeManagementDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnAddProfilePictureOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        LocalDate date = LocalDate.now();
        cbEmployeeManagementStatus.setOnAction(event -> {
            // Get the selected text and print it
            String selectedText = cbEmployeeManagementStatus.getSelectionModel().getSelectedItem();

        });

        Employee employee = new Employee(txtEmployeeManagementID.getText()
                ,txtEmployeeManagementName.getText(),
                txtNic.getText(),txtAddress.getText()
                ,date,txtEmployeeManagementBranch.getText()
                ,selectedStatus,
                txtEmployeeManagementContact.getText(),txtNic.getText(),selectedRole,txtEmployeeManagementUsername.getText(),imageBytes);
            EmployeeDataAccess.saveEmployee(employee);


    }

    public void btnDeleteProfilePictureOnAction(ActionEvent actionEvent) {
    }

    public void btnImportOnAction(ActionEvent actionEvent) throws IOException {


        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",
                "*.jpeg", "*.jpg", "*.png", "*.gif", "*.bmp"));
        fileChooser.setTitle("Open image files");
        File openFile = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (openFile == null) {
            ivEmployeeManagement.setImage(null);
            return;
        }
        BufferedImage bufImage = ImageIO.read(openFile);
        WritableImage fxImage = SwingFXUtils.toFXImage(bufImage, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufImage, "png", baos);
        imageBytes = baos.toByteArray();

        ivEmployeeManagement.setImage(fxImage);
        ivEmployeeManagement.setFitHeight(88); // Set the desired width
        ivEmployeeManagement.setPreserveRatio(true);
        apEmployeeManagementImage.setTopAnchor(ivEmployeeManagement, (apEmployeeManagementImage.getHeight() - ivEmployeeManagement.getFitHeight()) / 2);
        apEmployeeManagementImage.setLeftAnchor(ivEmployeeManagement, (apEmployeeManagementImage.getWidth() - ivEmployeeManagement.getFitWidth()) / 2);
    }
}

