package com.challenge.challenge.controller;

import com.challenge.challenge.model.Account;
import com.challenge.challenge.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAccount() {
        Account accountToCreate = new Account();
        when(accountService.createAccount(accountToCreate)).thenReturn(accountToCreate);

        Account createdAccount = accountController.createAccount(accountToCreate);

        assertEquals(accountToCreate, createdAccount);
    }

    @Test
    public void testGetAccount() {
        Integer accountId = 1;
        Account account = new Account();
        when(accountService.getAccount(accountId)).thenReturn(account);

        Account retrievedAccount = accountController.getAccount(accountId);

        assertEquals(account, retrievedAccount);
    }

    @Test
    public void testUpdateAccount() {
        Integer accountId = 1;
        Account accountToUpdate = new Account();
        when(accountService.updateAccount(accountId, accountToUpdate)).thenReturn(accountToUpdate);

        Account updatedAccount = accountController.updateAccount(accountId, accountToUpdate);

        assertEquals(accountToUpdate, updatedAccount);
    }

    @Test
    public void testDeleteAccount() {
        Integer accountId = 1;

        assertDoesNotThrow(() -> accountController.deleteAccount(accountId));
        verify(accountService, times(1)).deleteAccount(accountId);
    }
}