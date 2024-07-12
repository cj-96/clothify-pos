package edu.icet.controller;

import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.SupplierBo;
import edu.icet.dto.Supplier;
import edu.icet.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {
    public JFXTextField txtSupplierId;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public JFXTextField txtCompany;
    public JFXTextField txtSearchId;
    public Button btnSave;
    public Button btnUpdate;

    private SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtSupplierId.setText(supplierBo.generateId());
        txtSupplierId.setDisable(true);
        btnUpdate.setDisable(true);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        Supplier supplier = supplierBo.getSupplier(txtSearchId.getText());
        if(supplier != null){
            txtSupplierId.setText(supplier.getId());
            txtName.setText(supplier.getName());
            txtEmail.setText(supplier.getEmail());
            txtContact.setText(supplier.getContact());
            txtCompany.setText(supplier.getCompany());
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
        }else{
            new Alert(Alert.AlertType.ERROR,"Supplier Not Founded !").show();
        }
    }



    public void btnDeteleOnAction(ActionEvent actionEvent) {
        Boolean delete = supplierBo.delete(txtSupplierId.getText());
        if (delete){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Deleted !").show();
            clear();
        }else{
            new Alert(Alert.AlertType.ERROR,"Supplier Not Deleted !").show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Supplier supplier = new Supplier(
                txtSupplierId.getText(),
                txtName.getText(),
                txtContact.getText(),
                txtEmail.getText(),
                txtCompany.getText()
        );
        Boolean saved = supplierBo.UpdateSupplier(supplier);

        if (saved){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Updated !").show();
            clear();
        }else{
            new Alert(Alert.AlertType.ERROR,"Supplier Not Updated !").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clear();
    }
    public void clear(){
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        txtSupplierId.setText(supplierBo.generateId());
        txtCompany.setText("");
        txtContact.setText("");
        txtName.setText("");
        txtEmail.setText("");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        Supplier supplier = new Supplier(
                txtSupplierId.getText(),
                txtName.getText(),
                txtContact.getText(),
                txtEmail.getText(),
                txtCompany.getText()
        );
        Boolean saved = supplierBo.saveSupplier(supplier);

        if (saved){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Added !").show();
            clear();
        }else{
            new Alert(Alert.AlertType.ERROR,"Supplier Not Added !").show();
        }
    }
}
