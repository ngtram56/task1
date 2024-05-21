package com.example.task.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VehicleDTO {

    private String vehicleName;
    private int year;
    private BigDecimal price;
    private String owner;
    private Long brandId;

}