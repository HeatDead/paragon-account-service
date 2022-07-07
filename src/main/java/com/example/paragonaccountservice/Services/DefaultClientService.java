package com.example.paragonaccountservice.Services;

import com.example.paragonaccountservice.Entities.ClientEntity;
import com.example.paragonaccountservice.Exceptions.RegistrationException;
import com.example.paragonaccountservice.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultClientService implements ClientService{
    private final ClientRepository userRepository;

    @Override
    public void register(String clientId, String clientSecret) throws RegistrationException {
        if(userRepository.findById(clientId).isPresent())
            throw new RegistrationException(
                    "Client with id: " + clientId + " already registered");

        String hash = BCrypt.hashpw(clientSecret, BCrypt.gensalt());
        userRepository.save(new ClientEntity(clientId, hash));
    }

    @Override
    public void checkCredentials(String clientId, String clientSecret) throws LoginException {
        Optional<ClientEntity> optionalUserEntity = userRepository
                .findById(clientId);
        if (optionalUserEntity.isEmpty())
            throw new LoginException(
                    "Client with id: " + clientId + " not found");

        ClientEntity clientEntity = optionalUserEntity.get();

        if (!BCrypt.checkpw(clientSecret, clientEntity.getHash()))
            throw new LoginException("Secret is incorrect");
    }
}
