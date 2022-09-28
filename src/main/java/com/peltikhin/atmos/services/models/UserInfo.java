package com.peltikhin.atmos.services.models;

import com.peltikhin.atmos.auth.AuthUser;
import com.peltikhin.atmos.jpa.models.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserInfo {
    private Long id;
    private String username;

    public UserInfo(User registeredUser) {
        this.id = registeredUser.getId();
        this.username = registeredUser.getUsername();
    }

    public void fromAuthUser(AuthUser authUser) {
        this.id = authUser.getId();
        this.username = authUser.getUsername();
    }
}
