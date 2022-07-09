package com.example.paragonaccountservice.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.paragonaccountservice.Entities.ClientEntity;
import com.example.paragonaccountservice.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        Instant exp = now.plus(15, ChronoUnit.MINUTES);

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
}
