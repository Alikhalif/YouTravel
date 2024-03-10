package com.youcode.youtravel.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class PlaceGroupDTO {
    @NotBlank(message = "Place name Should not be Empty")
    private String place_name;

    private LocalDateTime time;

    @NotNull(message = "group id should not be null")
    private Long id_group;
}
