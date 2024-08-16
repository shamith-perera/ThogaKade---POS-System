package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeFormController {

    @FXML
    void btnExitOnAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnOpenAddCustomerOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/add_customer_form.fxml"))));
            stage.setTitle("Add Customer");
            stage.setResizable(false);
            stage.getIcons().add(new Image("icon/customer.png"));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnOpenViewCustomersOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/view_customers_form.fxml"))));
            stage.setTitle("View Customers");
            stage.setResizable(false);
            stage.getIcons().add(new Image("icon/customer.png"));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void btnOpenManageCustomerOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manage_customer_form.fxml"))));
            stage.setTitle("Manage Customer");
            stage.setResizable(false);
            stage.getIcons().add(new Image("icon/customer.png"));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
