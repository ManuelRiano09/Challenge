package com.challenge.challenge.controller;

import com.challenge.challenge.model.Movement;
import com.challenge.challenge.service.MovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MovementsControllerTest {

    @InjectMocks
    private MovementsController movementsController;

    @Mock
    private MovementService movementService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMovement() {
        Movement movementToCreate = new Movement();
        when(movementService.createMovement(movementToCreate)).thenReturn(movementToCreate);

        Movement createdMovement = movementsController.createMovement(movementToCreate);

        assertEquals(movementToCreate, createdMovement);
    }

    @Test
    public void testGetMovement() {
        Integer movementId = 1;
        Movement movement = new Movement();
        when(movementService.getMovement(movementId)).thenReturn(movement);

        Movement retrievedMovement = movementsController.getMovement(movementId);

        assertEquals(movement, retrievedMovement);
    }

    @Test
    public void testUpdateMovement() {
        Integer movementId = 1;
        Movement movementToUpdate = new Movement();
        when(movementService.updateMovement(movementId, movementToUpdate)).thenReturn(movementToUpdate);

        Movement updatedMovement = movementsController.updateMovement(movementId, movementToUpdate);

        assertEquals(movementToUpdate, updatedMovement);
    }

    @Test
    public void testDeleteMovement() {
        Integer movementId = 1;

        assertDoesNotThrow(() -> movementsController.deleteMovement(movementId));
        verify(movementService, times(1)).deleteMovement(movementId);
    }
}