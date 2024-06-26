package edu.icet.controller.customer;

import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.CustomerBo;
import edu.icet.util.BoType;
import edu.icet.util.CrudUtil;
import edu.icet.dto.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController {

    private static CustomerController instance;

    private CustomerController(){}



    public Boolean addCustomer(Customer customer){
        try {
            String sql = ("INSERT INTO customer VALUES (?,?,?,?,?,?,?,?,?)");
            return CrudUtil.execute(sql,
                                customer.getId(),
                                customer.getTitle(),
                                customer.getName(),
                                customer.getDob(),
                                customer.getSalary(),
                                customer.getAddress(),
                                customer.getCity(),
                                customer.getProvince(),
                                customer.getPostalCode());

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer searchCustomer(String customer ){

        try {

            ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer WHERE CustId='"+customer+"'" );
            while (resultSet.next()){

                return new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getDouble(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                );

            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ObservableList<Customer> loadCustomers() {

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");
            ObservableList<Customer> customerList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getDouble(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                );
                customerList.add(customer);
            }
            return customerList;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static CustomerController getInstance(){
        if (instance == null){
            return instance = new CustomerController();
        }else{
            return instance;
        }
    }

}
