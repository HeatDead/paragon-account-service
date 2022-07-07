package com.example.paragonaccountservice.Services;

import com.example.paragonaccountservice.Exceptions.RegistrationException;

import javax.security.auth.login.LoginException;

public interface ClientService {
    void register(String clientId, String clientSecret) throws RegistrationException;
    void checkCredentials(String clientId, String clientSecret) throws LoginException;
}
