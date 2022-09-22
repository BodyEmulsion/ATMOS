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
    @JoinColumn(name = "user_id")
    private User user;
}
