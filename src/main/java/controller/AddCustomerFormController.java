package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import util.AlertUtils;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private DatePicker dateDOB;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    private DBConnection db = DBConnection.getInstance();

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
        String title = cmbTitle.getValue();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contactNumber = txtContact.getText();
        LocalDate dateOfBirth = dateDOB.getValue();

        try {
            if (title == null || name.isEmpty() || address.isEmpty() || contactNumber.isEmpty() || dateOfBirth == null) {
                AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled.");
                return;
            }

            db.addCustomer(title, name, address, contactNumber, dateOfBirth);
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Success", "Customer added successfully.");
            setCustomerId();
            clearFields();
        } catch (RuntimeException ex) {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("MR. ");
        titles.add("Miss. ");
        cmbTitle.setItems(titles);
        setCustomerId();
    }

    private void setCustomerId(){
        String customerId = db.generateCustomerId();
        txtId.setText(customerId);
    }

    private void clearFields(){
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        dateDOB.setValue(null);
    }
}
