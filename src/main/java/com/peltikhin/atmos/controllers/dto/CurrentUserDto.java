package com.peltikhin.atmos.controllers.dto;

import com.peltikhin.atmos.services.models.CurrentUserInfo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CurrentUserDto {
    private Long id;
    private String username;

    public CurrentUserDto(CurrentUserInfo currentUserInfo) {
        this.id = currentUserInfo.getId();
        this.username = currentUserInfo.getUsername();
    }
}
