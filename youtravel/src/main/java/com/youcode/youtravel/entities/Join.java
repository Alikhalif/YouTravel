package com.youcode.youtravel.entities;

import com.youcode.youtravel.utils.JoinID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "join_group")
public class Join {
    @EmbeddedId
    private JoinID joinID;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "user id should not be null")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "group id should not be null")
    private Group group;
}
