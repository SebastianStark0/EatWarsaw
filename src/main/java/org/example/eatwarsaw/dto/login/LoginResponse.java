package org.example.eatwarsaw.dto.login;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.eatwarsaw.dto.UserDto;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private UserDto user;
}
