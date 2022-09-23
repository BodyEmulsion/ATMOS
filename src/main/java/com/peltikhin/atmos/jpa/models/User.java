package com.peltikhin.atmos.jpa.models;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //TODO make username unique or come up with a better way to find users in authorization process
    private String username;
    private String password;
    @OneToMany(mappedBy = "user")
    private Collection<Settings> settings;
    @OneToMany(mappedBy = "user")
    private Collection<Project> projects;
}
