package edu.icet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SupplierEntity {
    @Id
    private String id;
    private String name;
    private String contact;
    private String email;
    private String company;
}
