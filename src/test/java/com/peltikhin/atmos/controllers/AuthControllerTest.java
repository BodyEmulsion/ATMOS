package com.peltikhin.atmos.controllers;

import com.peltikhin.atmos.AbstractIntegrationTest;
import com.peltikhin.atmos.controllers.dto.RegisterDto;
import com.peltikhin.atmos.controllers.dto.UserDto;
import com.peltikhin.atmos.jpa.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

class AuthControllerTest extends AbstractIntegrationTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private Integer port;

    @BeforeEach
    public void beforeEach() {
        this.userRepository.deleteAll();
    }

    /**
     * Test that registration, login and check endpoints work correctly with correct input
     */
    @Test
    void authenticationWithCorrectInput() throws Exception {
        final String baseAuthUri = String.format("http://localhost:%d/auth/", port);
        final URI registerUri = new URI(baseAuthUri + "register");
        final URI loginUri = new URI(baseAuthUri + "login");
        final URI checkUri = new URI(baseAuthUri + "check");
        final String password = "password";
        final String username = "username";

        //Registration request:
        RegisterDto user = RegisterDto.builder()
                .username(username)
                .password(password)
                .build();
        HttpEntity<RegisterDto> registerRequest = new HttpEntity<>(user);
        ResponseEntity<UserDto> registerResponse = this.testRestTemplate.postForEntity(registerUri, registerRequest, UserDto.class);
        Assertions.assertEquals(200, registerResponse.getStatusCodeValue());

        //Login request:
        MultiValueMap<String, String> loginBody = new LinkedMultiValueMap<>();
        loginBody.add("username", username);
        loginBody.add("password", password);
        HttpHeaders loginHeaders = new HttpHeaders();
        loginHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, String>> loginRequest = new HttpEntity<>(loginBody, loginHeaders);
        ResponseEntity<UserDto> loginResponse = this.testRestTemplate.postForEntity(loginUri, loginRequest, UserDto.class);
        Assertions.assertEquals(200, loginResponse.getStatusCodeValue());

        //Check request:
        HttpHeaders headersWithCookies = new HttpHeaders();
        headersWithCookies.add(HttpHeaders.COOKIE, loginResponse.getHeaders().getFirst(HttpHeaders.SET_COOKIE));
        ResponseEntity<UserDto> checkResponse = this.testRestTemplate.postForEntity(checkUri, new HttpEntity<>(headersWithCookies), UserDto.class);
        Assertions.assertEquals(200, checkResponse.getStatusCodeValue());

        //Assert that the user is the same in all requests
        Assertions.assertEquals(registerResponse.getBody(), loginResponse.getBody());
        Assertions.assertEquals(registerResponse.getBody(), checkResponse.getBody());
    }
}