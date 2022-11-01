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
public class Notification implements OwnerIdProvider{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
    @Column(name = "task_id", nullable = false, updatable = false, insertable = false)
    private Long taskId;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date time;

    @Override
    public Long getOwnerId() {
        return task.getProject().getUserId();
    }
}
