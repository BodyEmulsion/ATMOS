package com.peltikhin.atmos.jpa.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "notifications")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date time;
}
