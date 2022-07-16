package com.example.paragonaccountservice.Services;

public interface TokenService {
    String generateToken(String clientId);
    boolean checkToken(String token) throws Exception;
}
