package com.example.paragonaccountservice.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.paragonaccountservice.Entities.ClientEntity;
import com.example.paragonaccountservice.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class DefaultTokenService implements TokenService{
    @Value("${auth.jwt.secret}")
    private String secretKey;

    private final ClientRepository clientRepository;

    @Override
    public String generateToken(String clientId) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        ClientEntity clientEntity = clientRepository.findById(clientId).get();

        Instant now = Instant.now();
        Instant exp = now.plus(7, ChronoUnit.DAYS);

        System.out.println("Creating token " + clientEntity.getRole());

        return JWT.create()
                .withIssuer("auth-service")
                .withAudience("paragon")
                .withSubject(clientId)
                .withClaim("role", clientEntity.getRole())
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(exp))
                .sign(algorithm);
    }

    public boolean checkToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();

        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            System.out.println(decodedJWT.getClaim("role"));
            if (!decodedJWT.getIssuer().equals("auth-service")) {
                System.out.println("Issuer is incorrect");
                return false;
            }

            if (!decodedJWT.getAudience().contains("paragon")) {
                System.out.println("Audience is incorrect");
                return false;
            }
        } catch (JWTVerificationException e) {
            System.out.println("Token is invalid: " + e.getMessage());
            return false;
        }

        return true;
    }
}
