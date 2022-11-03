package com.peltikhin.atmos.jpa.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Entity(name = "settings")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Time dayChangeTime;
    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private User user;
    @Column(name = "user_id")
    private Long userId;
}
