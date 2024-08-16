package controller;

import com.jfoenix.controls.JFXButton;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewCustomersFormController implements Initializable {

    public TableView tblCustomers;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTitle;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        reloadTable();
    }


    public void btnReloadOnAction(ActionEvent actionEvent) {
        reloadTable();
    }

    private void reloadTable(){
        List<Customer> customerList = DBConnection.getInstance().getAllCustomers();
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        customerList.forEach(obj->{
            customerObservableList.add(obj);
        });
        tblCustomers.setItems(customerObservableList);
        tblCustomers.refresh();
    }
}
