package com.challenge.challenge.service;

import com.challenge.challenge.Exception.ExceptionChallenge;
import com.challenge.challenge.model.Account;
import com.challenge.challenge.model.Movement;
import com.challenge.challenge.repository.MovementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class MovementServiceTest {

    @InjectMocks
    private MovementService movementService;

    @Mock
    private MovementRepository movementRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private ReportsService reportsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testCreateInvalidMovementType() {
        Movement invalidMovement = new Movement();
        invalidMovement.setMovementType("Invalido");

        assertThrows(ExceptionChallenge.class, () -> movementService.createMovement(invalidMovement));

        verify(accountService, never()).getAccount(anyInt());
        verify(accountService, never()).updateBalanceInAcccount(any(Account.class), anyDouble());
        verify(reportsService, never()).createCreditReport(any(Account.class), any(Movement.class), anyDouble(), anyDouble());
        verify(reportsService, never()).createDebitReport(any(Account.class), any(Movement.class), anyDouble(), anyDouble());
        verify(movementRepository, never()).save(any(Movement.class));
    }

    @Test
    public void testGetMovement() {
        Integer movementId = 1;
        Movement movement = new Movement();

        when(movementRepository.findById(anyInt())).thenReturn(Optional.of(movement));

        movementService.getMovement(movementId);

        verify(movementRepository, times(1)).findById(eq(movementId));
    }

    @Test
    public void testGetMovementNonexistent() {
        Integer movementId = 1;

        when(movementRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ExceptionChallenge.class, () -> movementService.getMovement(movementId));

        verify(movementRepository, times(1)).findById(eq(movementId));
    }

    @Test
    public void testUpdateMovement() {
        Integer movementId = 1;
        Movement updatedMovement = new Movement();
        Movement existingMovement = new Movement();

        when(movementRepository.findById(anyInt())).thenReturn(Optional.of(existingMovement));
        when(movementRepository.save(any(Movement.class))).thenReturn(updatedMovement);

        movementService.updateMovement(movementId, updatedMovement);

        verify(movementRepository, times(1)).findById(eq(movementId));
        verify(movementRepository, times(1)).save(eq(existingMovement));
    }

    @Test
    public void testUpdateMovementNonexistent() {
        Integer movementId = 1;
        Movement updatedMovement = new Movement();

        when(movementRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ExceptionChallenge.class, () -> movementService.updateMovement(movementId, updatedMovement));

        verify(movementRepository, times(1)).findById(eq(movementId));
    }

    @Test
    public void testDeleteMovement() {
        Integer movementId = 1;

        movementService.deleteMovement(movementId);

        verify(movementRepository, times(1)).deleteById(eq(movementId));
    }
}