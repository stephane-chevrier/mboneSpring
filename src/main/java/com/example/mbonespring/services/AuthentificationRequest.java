package com.example.mbonespring.services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthentificationRequest {
    private String username;
    private String password;
}