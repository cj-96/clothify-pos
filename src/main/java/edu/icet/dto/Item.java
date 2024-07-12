package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
    private String id;
    private String name;
    private String description;
    private String supplierId;
    private String category;
    private List<ItemDetail> itemDetailList;
}
