package com.challenge.challenge.service;

import com.challenge.challenge.Exception.ExceptionChallenge;
import com.challenge.challenge.model.Account;
import com.challenge.challenge.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAccount() {
        Account accountToCreate = new Account();
        when(accountRepository.save(accountToCreate)).thenReturn(accountToCreate);

        Account createdAccount = accountService.createAccount(accountToCreate);

        assertEquals(accountToCreate, createdAccount);
    }

    @Test
    public void testGetAccount() {
        Integer accountId = 1;
        Account account = new Account();
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        Account retrievedAccount = accountService.getAccount(accountId);

        assertEquals(account, retrievedAccount);
    }

    @Test
    public void testGetAccountNonexistent() {
        Integer accountId = 1;
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(ExceptionChallenge.class, () -> accountService.getAccount(accountId));
    }

    @Test
    public void testUpdateAccount() {
        Integer accountId = 1;
        Account updatedAccount = new Account();
        Account existingAccount = new Account();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(existingAccount)).thenReturn(updatedAccount);

        Account result = accountService.updateAccount(accountId, updatedAccount);

        assertEquals(updatedAccount, result);
    }

    @Test
    public void testUpdateAccountNonexistent() {
        Integer accountId = 1;
        Account updatedAccount = new Account();

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(ExceptionChallenge.class, () -> accountService.updateAccount(accountId, updatedAccount));
    }

    @Test
    public void testDeleteAccount() {
        Integer accountId = 1;

        assertDoesNotThrow(() -> accountService.deleteAccount(accountId));
        verify(accountRepository, times(1)).deleteById(accountId);
    }

    @Test
    public void testUpdateBalanceInAccount() {
        Account account = new Account();
        double balance = 1000.0;

        assertDoesNotThrow(() -> accountService.updateBalanceInAcccount(account, balance));

        assertEquals(balance, account.getInitialBalance(), 0.01);
        verify(accountRepository, times(1)).save(account);
    }
}