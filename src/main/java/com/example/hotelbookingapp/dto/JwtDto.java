package com.example.hotelbookingapp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class JwtDto {

    private String token;

    public JwtDto(String token) {
        this.token = token;
    }
}
