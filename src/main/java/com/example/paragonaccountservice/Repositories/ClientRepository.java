package com.example.paragonaccountservice.Repositories;

import com.example.paragonaccountservice.Entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, String> {
}
