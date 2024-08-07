package edu.icet.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userName;
    private String password;
    private String email;
    private String userType;
}
