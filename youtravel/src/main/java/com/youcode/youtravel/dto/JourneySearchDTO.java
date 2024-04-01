package com.youcode.youtravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JourneySearchDTO {
    private String countryStarting;
    private String stateStarting;
    private String cityStarting;
    private String countryArrival;
    private String stateArrival;
    private String cityArrival;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
