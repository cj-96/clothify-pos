package edu.icet.dto;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    private String userName;
    private String password;
    private String email;
    private String userType;
}
