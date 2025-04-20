package com.so.wallet.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String prenom;
    private String email;
    private String password;
    private String role;
}
