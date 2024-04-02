package com.youcode.youtravel.dto.ResponseDto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class PlaceGroupDTOResp {
    private Long id;

    private String place_name;

    private LocalDateTime time;

    private Long id_group;
}
