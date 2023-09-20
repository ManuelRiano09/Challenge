package com.challenge.challenge.repository;

import com.challenge.challenge.model.Client;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface ClientRepository extends CrudRepository<Client, Integer > {
}
