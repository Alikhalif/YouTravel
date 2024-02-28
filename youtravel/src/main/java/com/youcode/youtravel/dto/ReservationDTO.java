package com.youcode.youtravel.dto;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ReservationDTO {
    @NotNull(message = "Place Reserved should not be null")
    @Min(value = 1, message = "Place Reserved should be at least 1")
    private Integer placeReserved;

    private Double cost;

    @NotNull(message = "user id should not be null")
    private Long user_id;

    @NotNull(message = "journey id should not be null")
    private Long journey_id;
}
