package edu.icet.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartTM {
    private String itemCode;
    private String desc;
    private Integer qty;
    private Double unitPrice;
    private Double total;
    private Double discount;

}
