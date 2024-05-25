package com.youcode.youtravel.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_travel")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @NotBlank(message = "Starting country Should not be Empty")
    private String countryStarting;

    @NotBlank(message = "Starting city Should not be Empty")
    private String cityStarting;

    @NotBlank(message = "Arrival country Should not be Empty")
    private String countryArrival;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "user id should not be null")
    private User user;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Join> joinList;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<PlacesGroup> PlaceGroupList;
}

