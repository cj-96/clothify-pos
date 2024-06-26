package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Items {
    private String itemCode;
    private String desc;
    private String packSize;
    private Double unitPrice;
    private Integer qty;
}
