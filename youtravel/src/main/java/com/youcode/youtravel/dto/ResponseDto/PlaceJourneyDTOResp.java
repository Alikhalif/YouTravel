package com.youcode.youtravel.dto.ResponseDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlaceJourneyDTOResp {
    private Long id;

    private String place_name;

    private LocalDateTime time;

    private Long id_group;
}
