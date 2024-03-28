package com.youcode.youtravel.dto;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ReservationDTO {
    @NotNull(message = "Place Reserved should not be null")
    @Positive(message = "Number Place should be a positive value")
    private Integer reservedPlaces;

    private Double cost;

    @NotNull(message = "user id should not be null")
    private Long user_id;

    @NotNull(message = "journey id should not be null")
    private Long journey_id;
}
