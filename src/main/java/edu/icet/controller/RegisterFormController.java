package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.CustomerBo;
import edu.icet.bo.custom.UserBo;
import edu.icet.dto.User;
import edu.icet.util.BoType;
import edu.icet.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.annotations.processing.SQL;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {
    public JFXTextField txtAdminUserName;
    public JFXTextField txtUserName;
    public JFXPasswordField txtAdminPassword;
    public JFXTextField txtEmail;
    public JFXComboBox cmbUserType;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtConfirmPassword;
    public JFXButton btnRegister;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        btnRegister.setDisable(true);
//        txtEmail.setDisable(true);
//        txtPassword.setDisable(true);
//        txtConfirmPassword.setDisable(true);
//        txtUserName.setDisable(true);
//        cmbUserType.setDisable(true);

        loadDropMenu();

    }

    private void loadDropMenu() {
        ObservableList<Object> items = FXCollections.observableArrayList();
        items.add("Admin");
        items.add("Employee");
        cmbUserType.setItems(items);
    }

    public void btnValidateOnAction(ActionEvent actionEvent) {
        String userName = txtAdminUserName.getText();
        String password = txtAdminPassword.getText();
        if(userBo.isExist(userName,password)){
                new Alert(Alert.AlertType.CONFIRMATION,"Verified !").show();
//            btnRegister.setDisable(false);
//            txtEmail.setDisable(false);
//            txtPassword.setDisable(false);
//            txtConfirmPassword.setDisable(false);
//            txtUserName.setDisable(false);
//            cmbUserType.setDisable(false);
        }else {
            new Alert(Alert.AlertType.ERROR,"Not Verified !").show();
        }
    }

    public void rbtnShowPasswordOnAction(ActionEvent actionEvent) {
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        System.out.println(" okay");
        User user = new User(
                txtUserName.getText(),
                txtPassword.getText(),
                txtEmail.getText(),
                cmbUserType.getSelectionModel().getSelectedItem().toString()
        );
        System.out.println(user);

        Boolean saved = userBo.saveUser(user);

        if (saved){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Added !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer Not Added !").show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
    }

    public void cmbUserTypeOnAction(ActionEvent actionEvent) {
        System.out.println(cmbUserType.getSelectionModel().getSelectedItem().toString());
    }
}
