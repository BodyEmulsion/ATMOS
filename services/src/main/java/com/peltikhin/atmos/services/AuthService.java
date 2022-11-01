package com.peltikhin.atmos.services;

import com.peltikhin.atmos.auth.PasswordEncoderService;
import com.peltikhin.atmos.auth.UserProvider;
import com.peltikhin.atmos.jpa.models.User;
import com.peltikhin.atmos.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoder;
    private final UserProvider user;

    @Autowired
    public AuthService(UserProvider user, UserRepository userRepository, PasswordEncoderService passwordEncoder) {
        this.user = user;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getCurrentUser() {
        return user.getUser();
    }

    public User register(String username, String password) {
        var createdUser = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        //TODO Add some validations, like unique username
        return this.userRepository.save(createdUser);
    }
}
