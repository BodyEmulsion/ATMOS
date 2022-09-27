package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.services.models.CurrentUserInfo;
import com.peltikhin.atmos.controllers.dto.CurrentUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/auth")
public class AuthController {
    private final CurrentUserInfo currentUser;

    @Autowired
    public AuthController(CurrentUserInfo currentUser) {
        this.currentUser = currentUser;
    }

    @RequestMapping("/check")
    public ResponseEntity<CurrentUserDto> getCurrentUser() {
        CurrentUserDto user = new CurrentUserDto(currentUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
