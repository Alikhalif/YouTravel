package com.youcode.youtravel.dto.ResponseDto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class GroupDTOResp {
    private Long num;
    private String countryStarting;

    private String cityStarting;

    private String countryArrival;

    private String cityArrival;

    private String fromPlace;

    private String toPlace;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private UserDTOResp userDTOResp;

}
