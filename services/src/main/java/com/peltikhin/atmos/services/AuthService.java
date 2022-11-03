package com.peltikhin.atmos.services;

import com.peltikhin.atmos.auth.PasswordEncoderService;
import com.peltikhin.atmos.auth.UserProvider;
import com.peltikhin.atmos.jpa.models.User;
import com.peltikhin.atmos.jpa.repositories.UserRepository;
import com.peltikhin.atmos.services.dto.UserDto;
import com.peltikhin.atmos.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoder;
    private final UserProvider user;
    private final UserMapper mapper;

    @Autowired
    public AuthService(UserProvider user, UserRepository userRepository, PasswordEncoderService passwordEncoder, UserMapper mapper) {
        this.user = user;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    public UserDto getCurrentUser() {
        return mapper.toDto(user.getUser());
    }

    public UserDto register(String username, String password) {
        var createdUser = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        //TODO Add some validations, like unique username
        return mapper.toDto(this.userRepository.save(createdUser));
    }
}
