package com.youcode.youtravel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "places_group")
@Inheritance
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlacesGroup extends Places{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_group")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "group id should not be null")
    private Group group;

}
