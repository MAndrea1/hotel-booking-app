package com.example.hotelbookingapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignUpUser {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phone;
    private String country;

    @NotBlank
    private Integer role;
}
