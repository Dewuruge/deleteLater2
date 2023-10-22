package lk.ijse.dep11.ea.controller;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;


import javafx.collections.ObservableList;
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
import java.io.ByteArrayInputStream;
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
    public TextField txtEmployeeManagementID;
    public TextField txtEmployeeManagementContact;
    public Button btnEmployeeManagementDelete;


    public ComboBox <String>cbEmployeeManagementStatus;
    public ComboBox <String>cbEmployeeManagementRole;

    public TextField txtEmployeeManagementUsername;
    private final static String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public Pane pDetails2;
    private final String [] role = {"HR","Employee","Admin","IT"};
    private final String [] branch = {"Galle","Panadura"};
    private final  String [] status = {"Temporary","Intern","Permanent","Probation"};
    public ImageView ivEmployeeManagement;
    public ImageView ivEmployeeStatus;
    public ImageView ivTermination;
    public Button btnImport;
    public AnchorPane apEmployeeManagementImage;
    public String selectedStatus;
    public String selectedRole;
    public Button btnNewEmployee;

    public String selectedBranch;
    public Button btnRefresh;
    public DatePicker dpDOB;
    public ComboBox <String> cbBranch;
    public Button btnLogout;
    byte[] imageBytes;



    public void initialize() throws SQLException {

        lblDate.setText(date);
        txtEmployeeManagementID.setEditable(false);

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
            if(cur != null){
                btnEmployeeManagementSave.setText("Update");
                btnEmployeeManagementDelete.setDisable(false);
                txtAddress.setText(cur.getAddress());
                dpDOB.setValue(cur.getDOB());
                txtEmployeeManagementID.setText(cur.getId());
                cbBranch.setValue(cur.getBranch());
                txtEmployeeManagementContact.setText(cur.getContact());
                txtNic.setText(cur.getNic());
                txtEmployeeManagementName.setText(cur.getName());
                cbEmployeeManagementRole.setValue(cur.getRole());
                cbEmployeeManagementStatus.setValue(cur.getStatus());
                txtEmployeeManagementUsername.setText(cur.getUsername());

                try {
                    BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(cur.getByteImg()));
                    WritableImage fxImage = SwingFXUtils.toFXImage(bufImage, null);
                    ivEmployeeManagement.setImage(fxImage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
            else {
                btnEmployeeManagementDelete.setDisable(true);
                btnEmployeeManagementSave.setText("Save");
            }
        });

        cbBranch.getSelectionModel().selectedItemProperty().addListener((ov,pre,cut) ->{
            selectedBranch = cbBranch.getSelectionModel().getSelectedItem();
        });
        cbEmployeeManagementStatus.getSelectionModel().selectedItemProperty().addListener((ov, prev, cur) ->{
            selectedStatus = cbEmployeeManagementStatus.getSelectionModel().getSelectedItem();;

        });
        cbEmployeeManagementRole.getSelectionModel().selectedItemProperty().addListener((ov, prev, cur) ->{
            selectedRole = cbEmployeeManagementRole.getSelectionModel().getSelectedItem();;

        });

//        txtEmployeeManagementID.setDisable(true);

        tblEmployeeDetails.getItems().addAll(EmployeeDataAccess.getAllEmployees());

        cbEmployeeManagementRole.getItems().addAll(role);
        cbEmployeeManagementStatus.getItems().addAll(status);
        cbBranch.getItems().addAll(branch);

        Platform.runLater(() ->{
            btnNewEmployee.fire();
            root.requestFocus();
        });txtSearch.getText();



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

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException {
        if(apEmployeeStatus.isVisible()){
            boolean found = EmployeeDataAccess.getAllEmployees().stream().anyMatch(employee ->txtSearch.getText().equals(employee.getId()));
            if(found){
                Employee employee = EmployeeDataAccess.getOneEmployee(txtSearch.getText());
                txtEmployeeStatusBranch.setText(employee.getBranch());
                txtEmployeeStatusContact.setText(employee.getContact());
                txtEmployeeStatusID.setText(employee.getId());
                txtEmployeeStatusName.setText(employee.getName());

                try {
                    BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(employee.getByteImg()));
                    WritableImage fxImage = SwingFXUtils.toFXImage(bufImage, null);
                    ivEmployeeStatus.setImage(fxImage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }
        else {
            boolean found = EmployeeDataAccess.getAllEmployees().stream().anyMatch(employee ->txtSearch.getText().equals(employee.getId()));
            if(found){
                Employee employee = EmployeeDataAccess.getOneEmployee(txtSearch.getText());
                txtTerminationBranch.setText(employee.getBranch());
                txtTerminationContact.setText(employee.getContact());
                txtTerminationID.setText(employee.getId());
                txtTerminationName.setText(employee.getName());

                try {
                    BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(employee.getByteImg()));
                    WritableImage fxImage = SwingFXUtils.toFXImage(bufImage, null);
                    ivTermination.setImage(fxImage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }


    }
    }

    public void btnEmployeeManagementSaveOnAction(ActionEvent actionEvent) throws IOException {
        LocalDate DOB = dpDOB.getValue();
        Employee employee = new Employee(
                txtEmployeeManagementID.getText().strip(),
                txtEmployeeManagementName.getText().strip(),
                txtNic.getText().strip(),
                txtAddress.getText().strip(),
                DOB,
                selectedBranch,
                selectedStatus,
                txtEmployeeManagementContact.getText().strip(),
                txtNic.getText().strip(),
                selectedRole,
                txtEmployeeManagementUsername.getText().strip(),
                imageBytes);


        try {
            if (btnEmployeeManagementSave.getText().equals("Save")){
                EmployeeDataAccess.saveEmployee(employee);
                tblEmployeeDetails.getItems().add(employee);
            }else{
                EmployeeDataAccess.updateEmployee(employee);
                ObservableList<Employee> employeeList = tblEmployeeDetails.getItems();
                Employee selectedCustomer = tblEmployeeDetails.getSelectionModel().getSelectedItem();
                employeeList.set(employeeList.indexOf(selectedCustomer), employee);
                tblEmployeeDetails.refresh();
            }
                btnNewEmployee.fire();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save the customer, try again").show();
        }



    }

    public void btnEmployeeManagementDeleteOnAction(ActionEvent actionEvent) {
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
        ImageIO.write(bufImage, "jpeg", baos);
        imageBytes = baos.toByteArray();

        ivEmployeeManagement.setImage(fxImage);
        ivEmployeeManagement.setFitHeight(88); // Set the desired width
        ivEmployeeManagement.setPreserveRatio(true);
  }

    public void btnNewEmployeeOnAction(ActionEvent actionEvent) {
        cbEmployeeManagementRole.getSelectionModel().clearSelection();
        cbEmployeeManagementStatus.getSelectionModel().clearSelection();
        TextField[] textFields ={txtAddress,txtNic,txtEmployeeManagementContact,txtEmployeeManagementName,
                txtEmployeeManagementUsername,txtEmployeeManagementID,txtEmployeeManagementContact};
        for (TextField textField : textFields) {
            textField.clear();

        }
        Platform.runLater(()->{
            txtEmployeeManagementName.requestFocus();
            dpDOB.setValue(null);
            cbBranch.getSelectionModel().clearSelection();
            cbEmployeeManagementStatus.getSelectionModel().clearSelection();
            cbEmployeeManagementRole.getSelectionModel().clearSelection();
            tblEmployeeDetails.getSelectionModel().clearSelection();
            ivEmployeeManagement.setImage(null);
        });
        try {
            String lastEmployeeId = EmployeeDataAccess.getLastCustomerId();
            if (lastEmployeeId == null) {
                txtEmployeeManagementID.setText("IJSE-001");
            } else {
                int newId = Integer.parseInt(lastEmployeeId.substring(6)) + 1;
                txtEmployeeManagementID.setText(String.format("IJSE-%03d", newId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to establish the database connection, try again").show();

        }
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) {
    }
}

