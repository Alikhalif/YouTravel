package com.youcode.youtravel.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
public class JourneyDTO {

    @NotBlank(message = "Starting country Should not be Empty")
    private String countryStarting;

    @NotBlank(message = "Starting city Should not be Empty")
    private String cityStarting;

    @NotBlank(message = "Arrival country Should not be Empty")
    private String countryArrival;

    @NotBlank(message = "Arrival city Should not be Empty")
    private String cityArrival;

    @NotBlank(message = "Starting place Should not be Empty")
    private String fromPlace;

    @NotBlank(message = "Place of arrival Should not be Empty")
    private String toPlace;

    @NotBlank(message = "Start Date Should not be Empty")
    private LocalDateTime startDate;

    @NotBlank(message = "End Date Should not be Empty")
    private LocalDateTime endDate;

    @Positive(message = "Total Cost should be a positive value")
    @NotNull(message = "Amount Should Not Be Null")
    private Double costTotal;

    @Positive(message = "Number Place should be a positive value")
    @NotNull(message = "Number Place Should Not Be Null")
    private Integer nbrPlaces;

    @NotNull(message = "car id should not be null")
    private Long car_id;

    @NotNull(message = "user id should not be null")
    private Long user_id;
}
