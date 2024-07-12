package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDetail {

    private String itemCode;
    private String size;
    private String qty;
    private Double buyingPrice;
    private Double sellingPrice;
}
