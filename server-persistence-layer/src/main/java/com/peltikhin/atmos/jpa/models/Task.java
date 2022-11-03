package com.peltikhin.atmos.jpa.models;

import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity(name = "tasks")
@Getter
@Setter()
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task implements OwnerIdProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false, insertable = false, updatable = false)
    private Project project;
    @Column(name = "project_id")
    private Long projectId;
    @ManyToOne
    @JoinColumn(name = "block_id", insertable = false, updatable = false)
    private Block block;
    @Column(name = "block_id")
    private Long blockId;
    @OneToMany(mappedBy = "task")
    private Collection<Notification> notifications;
    @Setter(AccessLevel.NONE)
    @Formula("block_id is NOT NULL")
    private boolean planned;
    private Date created;

    @Override
    public Long getOwnerId() {
        return project.getUserId();
    }
}
