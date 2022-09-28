package com.peltikhin.atmos.services;

import com.peltikhin.atmos.jpa.models.User;
import com.peltikhin.atmos.jpa.repositories.UserRepository;
import com.peltikhin.atmos.services.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInfo userInfo;

    @Autowired
    public AuthService(UserInfo userInfo, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userInfo = userInfo;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserInfo getCurrentUser() {
        return this.userInfo;
    }

    public UserInfo register(String username, String password) {
        var createdUser = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        var registeredUser = this.userRepository.save(createdUser);
        //TODO Add some validations, like unique username
        return new UserInfo(registeredUser);
    }
}
