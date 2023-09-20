package com.challenge.challenge.service;

import com.challenge.challenge.Exception.ExceptionChallenge;
import com.challenge.challenge.model.Client;
import com.challenge.challenge.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client) {


        return clientRepository.save(client);
    }

    public Client getClient(Integer id) {
        Optional<Client> client =  clientRepository.findById(id);

        if (client.isPresent()){
            return client.get();
        }else {
            throw new ExceptionChallenge("id del cliente no existe");
        }
    }

    public Client updateClient(Integer id, Client updatedClient) {
        Client existingClient = getClient(id);

        if (existingClient != null) {
            existingClient.setName(updatedClient.getName());
            existingClient.setGender(updatedClient.getGender());
            existingClient.setAge(updatedClient.getAge());
            existingClient.setAddress(updatedClient.getAddress());
            existingClient.setPhone(updatedClient.getPhone());
            existingClient.setPassword(updatedClient.getPassword());
            existingClient.setStatus(updatedClient.getStatus());
            return clientRepository.save(existingClient);
        }

        throw new ExceptionChallenge("error al buscar el cliente existente");
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }
}
