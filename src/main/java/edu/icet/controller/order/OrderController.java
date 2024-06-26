package edu.icet.controller.order;

import edu.icet.controller.item.ItemController;
import edu.icet.db.DBConnection;
import edu.icet.dto.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {

    private static OrderController instance;

    private OrderController(){}

    public Boolean placeOrder(Order order){
    Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement("INSERT INTO orders VALUES(?,?,?)");
            connection.setAutoCommit(false);
            psTm.setString(1, order.getOrderId());
            psTm.setObject(2, order.getOrderDate());
            psTm.setString(3, order.getCustomerId());
            boolean isOrderNotAdd = psTm.execute();

            if (!isOrderNotAdd){
                Boolean isOrderDetailsAdd = OrderDetailController.getInstance().addOrderDetail(order.getOrderDetailList());
                if (isOrderDetailsAdd){
                    Boolean isStockUpdated = ItemController.getInstance().updateStock(order.getOrderDetailList());
                    if (isStockUpdated){
                        connection.setAutoCommit(true);
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static OrderController getInstance(){
        if(instance == null){
            return instance = new OrderController();
        }
        return instance;
    }
}
