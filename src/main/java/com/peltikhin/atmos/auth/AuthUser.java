package com.peltikhin.atmos.auth;

import com.peltikhin.atmos.jpa.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class AuthUser implements UserDetails {
    private final User user;
    //This SonarLint issue doesn't react when I did what it recommended, and all works without that changes. I don't know what's wrong, and I did tired of figuring it up
    private final boolean authenticated;

    public AuthUser(User user) {
        this.user = user;
        this.authenticated = true;
    }

    public AuthUser() {
        this.user = new User();
        this.authenticated = false;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + user.getId() +
                ", username='" + user.getUsername() + '\'' +
                ", password='" + user.getPassword() + '\'' +
                '}';
    }
}
