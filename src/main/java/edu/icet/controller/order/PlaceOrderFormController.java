package edu.icet.controller.order;

import com.google.gson.Gson;
import edu.icet.controller.customer.CustomerController;
import edu.icet.controller.item.ItemController;
import edu.icet.util.CrudUtil;
import edu.icet.db.DBConnection;
import edu.icet.dto.*;
import edu.icet.dto.tm.CartTM;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceOrderFormController implements Initializable {
    public Label lblTime;
    public Label lblOrderId;
    public Label lblDate;
    public ComboBox cmbCustomerIds;
    public ComboBox cmbItemCodes;
    public Label lblName;
    public Label lblAddress;
    public Label lblSalary;
    public Label lblCity;
    public Label lblDescription;
    public Label lblUnitPrice;
    public Label lblQty;
    public Label lblPackSize;
    public TextField txtQty;
    public Label lblTotal;
    public TableView tblCart;
    public TableColumn colItemCode;
    public TableColumn colDesc;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;

    ObservableList<CartTM> cartList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDateAndTime();
        loadCustomerIds();
        loadItemIds();
        generateOrderId();

        cmbCustomerIds.getSelectionModel().selectedItemProperty().addListener
                ((observable, oldValue, newValue) -> {
                    setCustomerdata((String) newValue);
                });
        cmbItemCodes.getSelectionModel().selectedItemProperty().addListener
                ((observable, oldValue, newValue) -> {
                    setItemData((String) newValue);
                });
    }

    private void setItemData(String itemCode) {
        Item item = ItemController.getInstance().searchItem(itemCode);
        System.out.println(item);
//        lblDescription.setText(item.getDesc());
//        lblPackSize.setText(item.getPackSize());
//        lblUnitPrice.setText(String.valueOf(item.getUnitPrice()));
//        lblQty.setText(String.valueOf(item.getQty()));
    }

    private void setCustomerdata(String customer) {
        Customer customer1 = CustomerController.getInstance().searchCustomer(customer);

        lblName.setText(customer1.getName());
        lblAddress.setText(customer1.getAddress());
        lblCity.setText(customer1.getCity());
        lblSalary.setText(String.valueOf(customer1.getSalary()));

    }

    private void loadItemIds() {
        ObservableList<Item> items = ItemController.getInstance().loadItems();

        ObservableList<String> codes = FXCollections.observableArrayList();

        items.forEach(item -> {
//            codes.add(item.getItemCode());
        });

        cmbItemCodes.setItems(codes);
    }

    private void loadCustomerIds() {
        ObservableList<Customer> customers = CustomerController.getInstance().loadCustomers();

        ObservableList<String> ids = FXCollections.observableArrayList();

        customers.forEach(customer -> {
            ids.add(customer.getId());
        });

        cmbCustomerIds.setItems(ids);

    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline timeLine = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime time = LocalTime.now();
            lblTime.setText(
                    time.getHour() + " : " + time.getMinute() + " : " + time.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();
    }

    public void generateOrderId() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT COUNT(*) FROM orders");
            Integer count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt(1);

            }
            if (count == 0) {
                lblOrderId.setText("D001");
            }
            String lastOrderId = "";
            ResultSet resultSet1 = CrudUtil.execute("SELECT OrderID\n" +
                    "FROM orders\n" +
                    "ORDER BY OrderID DESC\n" +
                    "LIMIT 1;");
            if (resultSet1.next()) {
                lastOrderId = resultSet1.getString(1);
                Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
                Matcher matcher = pattern.matcher(lastOrderId);
                if (matcher.find()) {
                    int number = Integer.parseInt(matcher.group(1));
                    number++;
                    lblOrderId.setText(String.format("D%03d", number));
                } else {
                    new Alert(Alert.AlertType.WARNING, "hello").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        try {
            String orderId = lblOrderId.getText();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date orderDate = format.parse(lblDate.getText());
            String customerId = cmbCustomerIds.getValue().toString();

            List<OrderDetail> orderDetailList = new ArrayList<>();

            for (CartTM cartItem : cartList) {
                String odrId = lblOrderId.getText();
                String itemCode = cartItem.getItemCode();
                Integer qty = cartItem.getQty();
                Double discount = cartItem.getDiscount();

                orderDetailList.add(new OrderDetail(odrId, itemCode, qty, discount));
            }
            Order order = new Order(orderId, orderDate, customerId, orderDetailList);
            System.out.println(order);
            Boolean isOrderPlaced = OrderController.getInstance().placeOrder(order);

            if (isOrderPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION,
                        "ORDER PLACED SUCCESSFULLY").show();
                generateOrderId();
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }


    public void btnAddToCartOnAction(ActionEvent actionEvent) {

        String itemCode = (String) cmbItemCodes.getValue();
        String desc = lblDescription.getText();
        Integer qty = Integer.valueOf(txtQty.getText());
        Double unitPrice = Double.valueOf(lblUnitPrice.getText());
        Double total = qty * unitPrice;
        CartTM cartItem = new CartTM(itemCode, desc, qty, unitPrice, total, 0.0);

        int stockQty = Integer.parseInt(lblQty.getText());

        if (stockQty < qty) {
            new Alert(Alert.AlertType.ERROR, "DO NOT HAVE ENOUGH STOCK").show();
            return;
        }

        cartList.add(cartItem);
        tblCart.setItems(cartList);
        calculateTotal();

    }

    public void calculateTotal() {
        double ttl = 0;
        for (CartTM cartItem : cartList) {
            ttl += cartItem.getTotal();
        }
        lblTotal.setText(String.valueOf(ttl));
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
        btnAddToCartOnAction(actionEvent);
    }


    public void btnCommitOnAction(ActionEvent actionEvent) {
        try {
            System.out.println("commit");
            Connection connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(true);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnApiCallOnAction(ActionEvent actionEvent) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/todos/1");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = bufferedReader.readLine()) != null){
            response.append(inputLine);
        }
        bufferedReader.close();

        System.out.println(response);

        Gson gson = new Gson();
        Sample sample = gson.fromJson(response.toString(), Sample.class);

        System.out.println(sample);

    }
}
