package com.peltikhin.atmos.jpa.models;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "projects")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project implements OwnerIdProvider{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "user_id", nullable = false, updatable = false, insertable = false)
    private Long userId;
    @OneToMany(mappedBy = "project")
    private Collection<Block> blocks;
    @OneToMany(mappedBy = "project")
    private Collection<Task> tasks;

    @Override
    public Long getOwnerId() {
        return userId;
    }
}
