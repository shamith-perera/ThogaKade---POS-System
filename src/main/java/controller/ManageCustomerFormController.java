package controller;


import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.Customer;
import util.AlertUtils;

public class ManageCustomerFormController {

    @FXML
    private JFXTextField txtSearchField;

    @FXML
    private JFXTextField txtTitle;

    @FXML
    private JFXTextField txtDOB;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {
        String customerId = txtSearchField.getText();
        if (customerId.isEmpty()) {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", "Search Field is Empty");
            return;
        }
        try {
            Customer customer = DBConnection.getInstance().getCustomerDetails(customerId);
            txtId.setText(customer.getCustomerId());
            txtTitle.setText(customer.getTitle());
            txtName.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            txtContact.setText(customer.getContactNumber());
            txtDOB.setText(customer.getDateOfBirth().toString());
        }catch (RuntimeException ex) {
            clearFields();
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }

    }
    private void clearFields(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDOB.clear();
        txtTitle.clear();
    }

    public void btnUpdateCustomerOnAction(ActionEvent actionEvent) {
        if(txtId.getText().isEmpty()){
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", "No Customer to Update");
            return;
        }
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contactNumber = txtContact.getText();

        try {
            DBConnection db = DBConnection.getInstance();
            db.updateCustomer(txtId.getText(), name, address, contactNumber);
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Success", "Customer updated successfully.");
        } catch (RuntimeException ex) {
            txtName.setText(name);
            txtAddress.setText(address);
            txtContact.setText(contactNumber);
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }

    }

    public void btnDeleteCustomerOnAction(ActionEvent actionEvent) {
        if(txtId.getText().isEmpty()){
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", "No Customer to Delete");
            return;
        }
        try {
            DBConnection.getInstance().deleteCustomer(txtId.getText());
            clearFields();
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Success", "Customer deleted successfully.");
        } catch (RuntimeException ex) {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }
    }


}
