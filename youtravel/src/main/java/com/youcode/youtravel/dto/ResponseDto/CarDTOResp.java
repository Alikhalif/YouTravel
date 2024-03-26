package com.youcode.youtravel.dto.ResponseDto;

import com.youcode.youtravel.enums.CarType;
import com.youcode.youtravel.enums.EnergyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTOResp {
    private Long id;

    private String name;

    private String color;

    private CarType carType;

    private EnergyType energyType;

    private Long user_id;
}
