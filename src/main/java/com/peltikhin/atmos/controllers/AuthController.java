package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.controllers.dto.UserDto;
import com.peltikhin.atmos.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping("/check")
    public ResponseEntity<UserDto> getCurrentUser() {
        UserDto user = new UserDto(authService.getCurrentUser());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //TODO change method signature to RegisterUser in order to declarative validate input data
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestParam("username") String username,
                                            @RequestParam("password") String password) {
        var user = this.authService.register(username, password);
        //TODO Add exception handling after adding validation
        return new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
    }
}
