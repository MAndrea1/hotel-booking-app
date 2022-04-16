package com.example.hotelbookingapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginUser {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
