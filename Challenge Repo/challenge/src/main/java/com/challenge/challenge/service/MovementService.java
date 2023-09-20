package com.challenge.challenge.service;

import com.challenge.challenge.Exception.ExceptionChallenge;
import com.challenge.challenge.model.Account;
import com.challenge.challenge.model.Movement;
import com.challenge.challenge.repository.MovementRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class MovementService {

    private final String DEBITCONSTANT = "Debito";
    private final String CREDITCONSTANT = "Credito";

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ReportsService reportsService;

    public Movement createMovement(Movement movement) {

        String typeOfMovement = movement.getMovementType();

        if (Strings.isEmpty(typeOfMovement) || !typeOfMovement.equals(DEBITCONSTANT) &&
                !typeOfMovement.equals(CREDITCONSTANT)){

            throw new ExceptionChallenge("el tipo de movimiento no existe  (Debito || Credito");
        }

        Account account = accountService.getAccount(movement.getAccount().getAccountNumber());

        if (typeOfMovement.equals(CREDITCONSTANT)){
            double initialBalance = account.getInitialBalance();
            double newBalance = (initialBalance + movement.getValue());
            accountService.updateBalanceInAcccount(account, newBalance);
            movement.setBalance(" Deposito de " + movement.getValue());
            movement.setDate(LocalDateTime.now());
            reportsService.createCreditReport(account, movement, newBalance, initialBalance);
            return movementRepository.save(movement);
        }

        if (typeOfMovement.equals(DEBITCONSTANT)){

            double initialBalance = account.getInitialBalance();
            if (initialBalance < movement.getValue()){
                throw new ExceptionChallenge("Saldo insuficiente");

            }else{
                double newBalance = (initialBalance - movement.getValue());
                accountService.updateBalanceInAcccount(account, newBalance);
                movement.setBalance(" Retiro de " + movement.getValue());
                movement.setDate(LocalDateTime.now());
                reportsService.createDebitReport(account, movement, newBalance, initialBalance);
                return movementRepository.save(movement);
            }
        }
        return movementRepository.save(movement);
    }

    public Movement getMovement(Integer id) {
        Optional<Movement> movement = movementRepository.findById(id);

        if (movement.isPresent()){
            return movement.get();
        }else {
            throw new ExceptionChallenge("No existe el movimiento con ese ID");
        }
    }

    public Movement updateMovement(Integer id, Movement updatedMovement) {
        Movement existingMovement = getMovement(id);

        if (existingMovement != null) {
            existingMovement.setDate(updatedMovement.getDate());
            existingMovement.setMovementType(updatedMovement.getMovementType());
            existingMovement.setValue(updatedMovement.getValue());
            existingMovement.setBalance(updatedMovement.getBalance());
            return movementRepository.save(existingMovement);
        }

        throw new ExceptionChallenge("error al buscar el movimiento existente");
    }

    public void deleteMovement(Integer id) {
        movementRepository.deleteById(id);
    }


}
