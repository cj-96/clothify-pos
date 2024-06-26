package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private String orderId;
    private Date orderDate;
    private String customerId;
    private List<OrderDetail> orderDetailList;

}
