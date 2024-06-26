package edu.icet.controller.order;

import edu.icet.util.CrudUtil;
import edu.icet.dto.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailController {

    private static OrderDetailController instance;

    private OrderDetailController(){}

    public Boolean addOrderDetail(List<OrderDetail> orderDetailList){
        Boolean isAdded;
        for(OrderDetail orderDetail1 : orderDetailList){
            isAdded = addOrderDetail(orderDetail1);
            System.out.println(isAdded);
            if(!isAdded){
                System.out.println(isAdded);
                return false;
            }
        }
        return true;
    }

    public Boolean addOrderDetail(OrderDetail orderDetail){
        try {
            return CrudUtil.execute("INSERT INTO orderDetail VALUES(?,?,?,?)",
                    orderDetail.getOrderId(),
                    orderDetail.getItemCode(),
                    orderDetail.getQty(),
                    orderDetail.getDiscount()
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static OrderDetailController getInstance(){
        if (instance == null){
            return instance = new OrderDetailController();
        }
        return instance;
    }
}
