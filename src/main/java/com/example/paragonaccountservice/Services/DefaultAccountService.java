package com.example.paragonaccountservice.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.paragonaccountservice.Clients.MainServiceClient;
import com.example.paragonaccountservice.Entities.ClientEntity;
import com.example.paragonaccountservice.Objects.Account;
import com.example.paragonaccountservice.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAccountService implements AccountService{

    @Value("${auth.jwt.secret}")
    private String secretKey;

    private final TokenService tokenService;
    private final ClientRepository clientRepository;

    private final MainServiceClient mainServiceClient;

    @Override
    public Account getUserInfo(String token) {
        try {
            if (tokenService.checkToken(token)) {
                Account account = new Account();
                Algorithm algorithm = Algorithm.HMAC256(secretKey);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);

                ClientEntity clientEntity = clientRepository.findById(decodedJWT.getSubject()).get();

                account.setUsername(clientEntity.getClientId());
                account.setName(clientEntity.getName());
                account.setSurname(clientEntity.getSurname());
                account.setPatronymic(clientEntity.getPatronymic());

                return account;
            }
        }catch (Exception e){}
        return null;
    }

    @Override
    public List<Object> getUserCars(String token) throws Exception{
        if(tokenService.checkToken(token))
        {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            ClientEntity clientEntity = clientRepository.findById(decodedJWT.getSubject()).get();

            return mainServiceClient.getAllCarsOfUser(clientEntity.getClientId());
        }
        throw new AuthenticationException("");
    }
}
