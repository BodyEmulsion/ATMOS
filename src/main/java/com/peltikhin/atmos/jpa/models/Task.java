package com.peltikhin.atmos.jpa.models;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity(name = "tasks")
@Getter
@Setter()
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    @Column(name = "project_id", insertable = false, updatable = false)
    private Long projectId;
    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "block_id")
    private Block block;
    @Column(name = "block_id", insertable = false, updatable = false)
    private Long blockId;
    @OneToMany(mappedBy = "task")
    private Collection<Notification> notifications;
    @Setter(AccessLevel.NONE)
    private Boolean planned;
    private Date created;

    public void plan(Block block){
        this.block = block;
        this.planned = true;
    }

    //I googled it, and "unplan" is real word, even though spell checker says it's not
    public void  unplan(){
        this.block = null;
        this.planned = false;
    }
}
