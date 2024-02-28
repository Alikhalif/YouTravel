package com.youcode.youtravel.dto;

import com.youcode.youtravel.entities.User;
import com.youcode.youtravel.enums.CarType;
import com.youcode.youtravel.enums.EnergyType;
import lombok.Data;

@Data
public class CarDTO {

    private String name;

    private String color;

    private CarType carType;

    private EnergyType energyType;

    private Long user_id;
}
