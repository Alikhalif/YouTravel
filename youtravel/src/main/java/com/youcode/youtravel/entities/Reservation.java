package com.youcode.youtravel.entities;

import com.youcode.youtravel.utils.ReservationID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "raservation")
public class Reservation {
    @EmbeddedId
    private ReservationID reservationID;

    @Positive(message = "Number Place should be a positive value")
    @NotNull(message = "Number Place Should Not Be Null")
    private Integer reservedPlaces;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "user id should not be null")
    private User user;

    @ManyToOne
    @JoinColumn(name = "journey_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "journey id should not be null")
    private Journey journey;
}
