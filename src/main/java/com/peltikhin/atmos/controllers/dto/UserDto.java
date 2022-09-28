package com.peltikhin.atmos.controllers.dto;

import com.peltikhin.atmos.services.models.UserInfo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String username;

    public UserDto(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.username = userInfo.getUsername();
    }
}
