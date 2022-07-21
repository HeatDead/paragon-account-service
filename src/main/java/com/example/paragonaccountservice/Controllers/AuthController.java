package com.example.paragonaccountservice.Controllers;

import com.example.paragonaccountservice.Exceptions.RegistrationException;
import com.example.paragonaccountservice.Objects.User;
import com.example.paragonaccountservice.Responses.TokenResponse;
import com.example.paragonaccountservice.Services.ClientService;
import com.example.paragonaccountservice.Services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {
    private final ClientService clientService;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) throws RegistrationException {
        clientService.register(user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getPatronymic());
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody User user) throws LoginException {
        clientService.checkCredentials(
                user.getUsername(), user.getPassword());
        return new TokenResponse(
                tokenService.generateToken(user.getUsername()));
    }
}