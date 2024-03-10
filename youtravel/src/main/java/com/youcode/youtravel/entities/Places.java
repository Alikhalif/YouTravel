package com.youcode.youtravel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Places {
    @NotBlank(message = "Place Name Should not be Empty")
    @Column(nullable = false, length = 1000)
    private String place_name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private LocalDateTime time;
}
