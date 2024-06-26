package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetail {
    private String orderId;
    private String itemCode;
    private Integer qty;
    private Double discount;


}
