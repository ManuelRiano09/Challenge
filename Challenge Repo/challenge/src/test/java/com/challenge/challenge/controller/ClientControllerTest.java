package com.challenge.challenge.controller;

import com.challenge.challenge.model.Client;
import com.challenge.challenge.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateClient() {
        Client clientToCreate = new Client();
        when(clientService.createClient(clientToCreate)).thenReturn(clientToCreate);

        Client createdClient = clientController.createClient(clientToCreate);

        assertEquals(clientToCreate, createdClient);
    }

    @Test
    public void testGetClient() {
        Integer clientId = 1;
        Client client = new Client();
        when(clientService.getClient(clientId)).thenReturn(client);

        Client retrievedClient = clientController.getClient(clientId);

        assertEquals(client, retrievedClient);
    }

    @Test
    public void testUpdateClient() {
        Integer clientId = 1;
        Client clientToUpdate = new Client();
        when(clientService.updateClient(clientId, clientToUpdate)).thenReturn(clientToUpdate);

        Client updatedClient = clientController.updateClient(clientId, clientToUpdate);

        assertEquals(clientToUpdate, updatedClient);
    }

    @Test
    public void testDeleteClient() {
        Integer clientId = 1;

        assertDoesNotThrow(() -> clientController.deleteClient(clientId));
        verify(clientService, times(1)).deleteClient(clientId);
    }
}