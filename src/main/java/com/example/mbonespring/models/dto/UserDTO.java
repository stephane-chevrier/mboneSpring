
package com.example.mbonespring.models.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private String username;
    //    private String password;
    private String token;
    private int soldeCredit;
}
