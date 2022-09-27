package com.peltikhin.atmos.services.models;

import com.peltikhin.atmos.auth.AuthUser;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CurrentUserInfo {
    private Long id;
    private String username;

    public void fromAuthUser(AuthUser authUser) {
        this.id = authUser.getId();
        this.username = authUser.getUsername();
    }
}
