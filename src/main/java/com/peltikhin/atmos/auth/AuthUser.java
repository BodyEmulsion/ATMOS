package com.peltikhin.atmos.auth;

import com.peltikhin.atmos.jpa.models.User;
import lombok.Getter;

import java.util.HashSet;
import java.util.Objects;

@Getter
public class AuthUser extends org.springframework.security.core.userdetails.User {
    private final Long id;

    public AuthUser(User user) {
        super(user.getUsername(), user.getPassword(), new HashSet<>());
        this.id = user.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AuthUser that = (AuthUser) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
