package com.youcode.youtravel.dto.ResponseDto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReservationDTOResp {
    private Integer reservedPlaces;

//    private Long user_id;

//    private Long journey_id;
    private JourneyDTOResp journeyDTOResp;
}
