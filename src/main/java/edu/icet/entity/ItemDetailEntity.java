package edu.icet.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemDetailEntity {

    private String itemCode;
    private String size;
    private String qty;
    private Double buyingPrice;
    private Double sellingPrice;
}
