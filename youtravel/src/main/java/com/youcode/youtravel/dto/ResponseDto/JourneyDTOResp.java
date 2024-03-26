package com.youcode.youtravel.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JourneyDTOResp {
    private Long code;

    private String countryStarting;

    private String stateStarting;

    private String cityStarting;

    private String countryArrival;

    private String stateArrival;

    private String cityArrival;

    private String fromPlace;

    private String toPlace;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Double costTotal;

    private Integer nbrPlaces;

    private CarDTOResp carDTOResp;

    private UserDTOResp userDTOResp;
}
