package com.peltikhin.atmos.controllers.dto;

import lombok.*;

//TODO Add validation
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDto {
    private String username;
    private String password;
}
