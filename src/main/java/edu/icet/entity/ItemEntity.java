package edu.icet.entity;

import edu.icet.dto.ItemDetail;
import edu.icet.dto.OrderDetail;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private String supplierId;
    private String category;
}
