package com.youcode.youtravel.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class GroupDTO {
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

    @NotNull(message = "user id should not be null")
    private Long user_id;
}
