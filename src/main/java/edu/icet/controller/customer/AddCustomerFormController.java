package edu.icet.controller.customer;

import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.CustomerBo;
import edu.icet.db.DBConnection;
import edu.icet.dto.Customer;
import edu.icet.dto.tm.Table01TM;
import edu.icet.dto.tm.Table02TM;
import edu.icet.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;


public class AddCustomerFormController implements Initializable {
    public JFXTextField txtCustomerId;
    public ChoiceBox cmbTitle;
    public JFXTextField txtCustomerName;
    public DatePicker dateDob;
    public JFXTextField txtSalary;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;
    public TableView tblCustomer01;
    public TableColumn colCustomerId;
    public TableColumn colTitle;
    public TableColumn colName;
    public TableColumn colDob;
    public TableColumn colSalary;
    public TableView tblCustomer02;
    public TableColumn colAddress;
    public TableColumn colCustomerId02;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostalCode;

    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCustomerId02.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        loadDropMenu();
        loadTable01();
        loadTable02();
    }

    private void loadTable01() {
        ObservableList<Table01TM> table01Data = FXCollections.observableArrayList();
        ObservableList<Customer> customers = CustomerController.getInstance().loadCustomers();
        customers.forEach(customer ->{
            Table01TM table01 = new Table01TM(
                    customer.getId(),
                    customer.getTitle(),
                    customer.getName(),
                    customer.getDob(),
                    customer.getSalary()
            );
            table01Data.add(table01);
        });

        tblCustomer01.setItems(table01Data);

    }

    private void loadTable02() {
        ObservableList<Table02TM> table02Data = FXCollections.observableArrayList();
        ObservableList<Customer> customers = CustomerController.getInstance().loadCustomers();
        customers.forEach(customer ->{
            Table02TM table02 = new Table02TM(
                    customer.getId(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalCode()
            );
            table02Data.add(table02);
        });

        tblCustomer02.setItems(table02Data);

    }

    private void loadDropMenu() {
        ObservableList<Object> items = FXCollections.observableArrayList();
        items.add("MRS");
        items.add("MR");
        items.add("MS");
        items.add("MISS");
        cmbTitle.setItems(items);
    }

    public void btnAddOnAction(ActionEvent actionEvent){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = format.parse(dateDob.getValue().toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Customer customer = new Customer(
                txtCustomerId.getText(),
                cmbTitle.getValue().toString(),
                txtCustomerName.getText(),
                date,
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()
        );

        //boolean b = CustomerController.getInstance().addCustomer(customer);
        boolean b = customerBo.saveCustomer(customer);
        System.out.println(b);
        if (b){
            new Alert(Alert.AlertType.ERROR,"Customer Not Added !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Added !").show();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        try {
            boolean execute = DBConnection.getInstance().getConnection().createStatement().execute("DELETE FROM customer WHERE CustId='" + txtCustomerId.getText() + "'");
            System.out.println(execute);
            loadTable01();
            loadTable02();
            clearText();
            if (execute){
                System.out.println("Customer Deleted");
            }else {
                System.out.println("Customer not Deleted ");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws ParseException {
        Customer customer = CustomerController.getInstance().searchCustomer(txtCustomerId.getText());
        cmbTitle.setValue(customer.getTitle());
        txtCustomerName.setText(customer.getName());
        txtSalary.setText(String.valueOf(customer.getSalary()));
        txtAddress.setText(customer.getAddress());
        txtCity.setText(customer.getCity());
        txtProvince.setText(customer.getProvince());
        txtPostalCode.setText(customer.getPostalCode());
    }

    private void clearText(){
        txtCustomerId.setText(null);
        cmbTitle.setValue(null);
        txtCustomerName.setText(null);
        dateDob.setValue(null);
        txtSalary.setText(null);
        txtAddress.setText(null);
        txtCity.setText(null);
        txtProvince.setText(null);
        txtPostalCode.setText(null);




    }


}
