package com.example.paragonaccountservice.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.paragonaccountservice.Entities.ClientEntity;
import com.example.paragonaccountservice.Objects.Account;
import com.example.paragonaccountservice.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultAccountService implements AccountService{

    @Value("${auth.jwt.secret}")
    private String secretKey;

    private final TokenService tokenService;
    private final ClientRepository clientRepository;

    @Override
    public Account getUserInfo(String token) {
        if(tokenService.checkToken(token))
        {
            Account account = new Account();
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            ClientEntity clientEntity = clientRepository.findById(decodedJWT.getSubject()).get();

            account.setName(clientEntity.getName());
            account.setSurname(clientEntity.getSurname());
            account.setPatronymic(clientEntity.getPatronymic());

            return account;
        }
        return null;
    }
}
