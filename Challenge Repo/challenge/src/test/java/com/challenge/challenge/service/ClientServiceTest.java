package com.challenge.challenge.service;

import com.challenge.challenge.Exception.ExceptionChallenge;
import com.challenge.challenge.model.Client;
import com.challenge.challenge.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateClient() {
        Client clientToCreate = new Client();
        when(clientRepository.save(clientToCreate)).thenReturn(clientToCreate);

        Client createdClient = clientService.createClient(clientToCreate);

        assertEquals(clientToCreate, createdClient);
    }

    @Test
    public void testGetClient() {
        Integer clientId = 1;
        Client client = new Client();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        Client retrievedClient = clientService.getClient(clientId);

        assertEquals(client, retrievedClient);
    }

    @Test
    public void testGetClientNonexistent() {
        Integer clientId = 1;
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        assertThrows(ExceptionChallenge.class, () -> clientService.getClient(clientId));
    }

    @Test
    public void testUpdateClient() {
        Integer clientId = 1;
        Client updatedClient = new Client();
        Client existingClient = new Client();

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(updatedClient);

        Client result = clientService.updateClient(clientId, updatedClient);

        assertEquals(updatedClient, result);
    }

    @Test
    public void testUpdateClientNonexistent() {
        Integer clientId = 1;
        Client updatedClient = new Client();

        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        assertThrows(ExceptionChallenge.class, () -> clientService.updateClient(clientId, updatedClient));
    }

    @Test
    public void testDeleteClient() {
        Integer clientId = 1;

        assertDoesNotThrow(() -> clientService.deleteClient(clientId));
        verify(clientRepository, times(1)).deleteById(clientId);
    }
}