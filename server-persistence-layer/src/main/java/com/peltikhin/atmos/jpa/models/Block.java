package com.peltikhin.atmos.jpa.models;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity(name = "blocks")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Block implements OwnerIdProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false, insertable = false, updatable = false)
    private Project project;
    @Column(name = "project_id")
    private Long projectId;
    @OneToMany(mappedBy = "block")
    private Collection<Task> tasks;
    private Date timeStart;
    private Date timeEnd;

    @Override
    public Long getOwnerId() {
        return project.getUserId();
    }
}
