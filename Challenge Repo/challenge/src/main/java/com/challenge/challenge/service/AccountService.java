package com.challenge.challenge.service;

import com.challenge.challenge.Exception.ExceptionChallenge;
import com.challenge.challenge.model.Account;
import com.challenge.challenge.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccount(Integer id) {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isPresent()){
            return account.get();
        }else {
            throw new ExceptionChallenge("el id de la cuenta no existe");
        }
    }

    public Account updateAccount(Integer id, Account updatedAccount) {
        Account existingAccount = getAccount(id);

        if (existingAccount != null) {
            existingAccount.setAccountNumber(updatedAccount.getAccountNumber());
            existingAccount.setAccountType(updatedAccount.getAccountType());
            existingAccount.setInitialBalance(updatedAccount.getInitialBalance());
            existingAccount.setStatus(updatedAccount.getStatus());
            return accountRepository.save(existingAccount);
        }

        throw new ExceptionChallenge("error al buscar la cuenta existente");
    }

    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }

    public void updateBalanceInAcccount(Account account, double balance ){
        account.setInitialBalance(balance);
        accountRepository.save(account);
    }
}







