package com.youcode.youtravel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "journey")
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @NotBlank(message = "Starting country Should not be Empty")
    private String countryStarting;

    @NotBlank(message = "Starting state Should not be Empty")
    private String stateStarting;

    @NotBlank(message = "Starting city Should not be Empty")
    private String cityStarting;

    @NotBlank(message = "Arrival country Should not be Empty")
    private String countryArrival;

    @NotBlank(message = "Arrival state Should not be Empty")
    private String stateArrival;

    @NotBlank(message = "Arrival city Should not be Empty")
    private String cityArrival;

    @NotBlank(message = "Starting place Should not be Empty")
    private String fromPlace;

    @NotBlank(message = "Place of arrival Should not be Empty")
    private String toPlace;

    @Temporal(TemporalType.TIMESTAMP)
    @NotBlank(message = "Start Date Should not be Empty")
    private LocalDateTime startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @NotBlank(message = "End Date Should not be Empty")
    private LocalDateTime endDate;


    @Positive(message = "Number Place should be a positive value")
    @NotNull(message = "Number Place Should Not Be Null")
    private Integer nbrPlaces;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "car id should not be null")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "user id should not be null")
    private User user;

    @OneToMany(mappedBy = "journey", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Reservation> reservationList;

    @OneToMany(mappedBy = "journey", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<PlacesJourney> PlaceJourneyList;

}
