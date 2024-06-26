package edu.icet.controller.item;

import edu.icet.util.CrudUtil;
import edu.icet.dto.Items;
import edu.icet.dto.OrderDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemController {

    private static ItemController instance;

    private ItemController(){}

    public Items searchItem(String itemCode ){
        try {

            ResultSet resultSet = CrudUtil.execute("SELECT * FROM item WHERE itemCode='"+itemCode+"'" );
            while (resultSet.next()){

                return new Items(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5)
                );

            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ObservableList<Items> loadItems() {

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM item");
            ObservableList<Items> items = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Items item = new Items(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5)
                );
                items.add(item);
            }
            return items;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean updateStock(List<OrderDetail> orderDetailList) {
        Boolean isUpdated;
        for(OrderDetail orderDetail : orderDetailList){
            isUpdated = updateStock(orderDetail);
            System.out.println(isUpdated);
            if(!isUpdated){
                System.out.println(isUpdated);
                return false;
            }
        }
        return true;
    }

    private Boolean updateStock(OrderDetail orderDetail) {
        try {
            return CrudUtil.execute("UPDATE item SET QtyOnHand=QtyOnHand-? WHERE ItemCode = ?",orderDetail.getQty(),orderDetail.getItemCode());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ItemController getInstance(){
        if (instance == null){
            return instance = new ItemController();
        }else{
            return instance;
        }
    }

}
