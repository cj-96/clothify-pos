package edu.icet.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemTM {
    private String size;
    private String qty;
    private Double buyingPrice;
    private Double sellingPrice;
}
