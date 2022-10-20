package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.controllers.dto.RegisterDto;
import com.peltikhin.atmos.controllers.dto.UserDto;
import com.peltikhin.atmos.controllers.mappers.UserMapper;
import com.peltikhin.atmos.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserMapper userMapper;

    @Autowired
    public AuthController(AuthService authService, UserMapper userMapper) {
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @RequestMapping(path = "/check", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<UserDto> getCurrentUser() {
        UserDto user = userMapper.toDto(authService.getCurrentUser());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterDto registerDto) {
        UserDto user = userMapper.toDto(this.authService.register(registerDto.getUsername(), registerDto.getPassword()));
        //TODO Add exception handling after adding validation
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
