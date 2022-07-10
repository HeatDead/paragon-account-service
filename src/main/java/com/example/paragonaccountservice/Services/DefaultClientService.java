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
    public void register(String clientId, String clientSecret, String name, String surname, String patronymic) throws RegistrationException {
        if(userRepository.findById(clientId).isPresent())
            throw new RegistrationException(
                    "Client with id: " + clientId + " already registered");

        String hash = BCrypt.hashpw(clientSecret, BCrypt.gensalt());
        userRepository.save(new ClientEntity(clientId, hash, "CLIENT", name, surname, patronymic));
    }

    @Override
    public void checkCredentials(String clientId, String clientPassword) throws LoginException {
        Optional<ClientEntity> optionalUserEntity = userRepository
                .findById(clientId);
        if (optionalUserEntity.isEmpty())
            throw new LoginException(
                    "Client with id: " + clientId + " not found");

        ClientEntity clientEntity = optionalUserEntity.get();

        if (!BCrypt.checkpw(clientPassword, clientEntity.getHash()))
            throw new LoginException("Password is incorrect");
    }
}
