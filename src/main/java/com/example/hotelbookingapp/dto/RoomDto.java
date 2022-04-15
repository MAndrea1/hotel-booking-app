package com.example.hotelbookingapp.dto;

import com.example.hotelbookingapp.model.Facility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private Integer roomNumber;
    private Integer roomMaxPax;
    private String roomType;
    private BigDecimal roomPrice;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rooms_facilities",
            joinColumns = @JoinColumn(name = "room_number"),
            inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    private List<Facility> facilities = new ArrayList<>();
}
