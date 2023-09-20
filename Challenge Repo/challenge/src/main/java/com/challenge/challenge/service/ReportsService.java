package com.challenge.challenge.service;

import com.challenge.challenge.Exception.ExceptionChallenge;
import com.challenge.challenge.model.Account;
import com.challenge.challenge.model.Client;
import com.challenge.challenge.model.Movement;
import com.challenge.challenge.model.Report;
import com.challenge.challenge.repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportsService {

    @Autowired
    ReportsRepository reportsRepository;

    @Autowired
    ClientService clientService;


    public List<Report> getReportByDateAndClientId(String date1, String date2, Integer clientId){

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDateTime1 = null;
        LocalDateTime localDateTime2 = null;
        try {
            localDateTime1 = LocalDateTime.parse(date1, formatter);
            localDateTime2 = LocalDateTime.parse(date2, formatter);

        } catch (Exception e) {
            throw new ExceptionChallenge("Fechas ingresadas incorrectamente");
        }

        List<Report> reportOptional = reportsRepository.findAllByDateBetweenAndClientId(localDateTime1, localDateTime2, clientId);

        return reportOptional;

    }

    public void createCreditReport(Account account, Movement movement, double newBalance, double initialBalance){

        Client client = clientService.getClient(account.getClient().getIdentification());
        Report report = new Report();
        report.setDate(movement.getDate());
        report.setAccountNumber(account.getAccountNumber());
        report.setClientName(client.getName());
        report.setStatus(account.getStatus());
        report.setAccountType(account.getAccountType());
        report.setMovement(" + " + movement.getValue());
        report.setAvailableBalance(newBalance);
        report.setInitialBalance(initialBalance);
        report.setClientId(client.getIdentification());

        reportsRepository.save(report);

    }
    public void createDebitReport(Account account, Movement movement, double newBalance, double initialBalance){

        Client client = clientService.getClient(account.getClient().getIdentification());
        Report report = new Report();
        report.setDate(movement.getDate());
        report.setAccountNumber(account.getAccountNumber());
        report.setClientName(client.getName());
        report.setStatus(account.getStatus());
        report.setAccountType(account.getAccountType());
        report.setMovement(" - " + movement.getValue());
        report.setAvailableBalance(newBalance);
        report.setInitialBalance(initialBalance);
        report.setClientId(client.getIdentification());

        reportsRepository.save(report);

    }


}
