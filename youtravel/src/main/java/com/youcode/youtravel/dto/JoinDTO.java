package com.youcode.youtravel.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class JoinDTO {
    private LocalDateTime time;

    @NotNull(message = "user id should not be null")
    private Long user_id;

    @NotNull(message = "group id should not be null")
    private Long group_id;
}
