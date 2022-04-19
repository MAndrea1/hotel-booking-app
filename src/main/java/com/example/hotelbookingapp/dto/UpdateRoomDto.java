package com.example.hotelbookingapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Data
public class UpdateRoomDto {

    @NotBlank
    private Integer roomId;
    @NotBlank
    private Integer roomMaxpax;
    @NotBlank
    private BigDecimal roomPrice;
    private Character roomStatus;
    private Integer roomType;
    private List<Integer> listFacilities;
}
