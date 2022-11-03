package com.peltikhin.atmos.services.dto;

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
