package com.youcode.youtravel.entities;

import com.youcode.youtravel.enums.CarType;
import com.youcode.youtravel.enums.EnergyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Car Name Should not be Empty")
    private String name;

    @NotBlank(message = "Car Color Should not be Empty")
    private String color;

    @NotEmpty(message = "Car Type should not be empty")
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @NotEmpty(message = "Energy Type Type should not be empty")
    @Enumerated(EnumType.STRING)
    private EnergyType energyType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "user id should not be null")
    private User user;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Journey> journeyList;

}
