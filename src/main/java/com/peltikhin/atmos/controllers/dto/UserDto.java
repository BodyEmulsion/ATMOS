package com.peltikhin.atmos.controllers.dto;

import com.peltikhin.atmos.services.models.UserInfo;
import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (!Objects.equals(id, userDto.id)) return false;
        return Objects.equals(username, userDto.username);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
