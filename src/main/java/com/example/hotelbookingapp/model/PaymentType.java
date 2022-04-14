package com.example.hotelbookingapp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paymenttypes")
@Data
public class PaymentType {
    @Id
    @Column(name = "paymenttype_id", nullable = false)
    private Integer id;

    @Column(name = "paymenttypes_detail", length = 45)
    private String paymenttypesDetail;
}