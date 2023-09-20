package com.challenge.challenge.service;

import com.challenge.challenge.Exception.ExceptionChallenge;
import com.challenge.challenge.model.Account;
import com.challenge.challenge.model.Client;
import com.challenge.challenge.model.Movement;
import com.challenge.challenge.model.Report;
import com.challenge.challenge.repository.ReportsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReportsServiceTest {

    @InjectMocks
    private ReportsService reportsService;

    @Mock
    private ReportsRepository reportsRepository;

    @Mock
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReportByDateAndClientId() {
        String date1 = "2023-09-01T00:00:00";
        String date2 = "2023-09-30T23:59:59";
        Integer clientId = 1;

        LocalDateTime localDateTime1 = LocalDateTime.parse(date1);
        LocalDateTime localDateTime2 = LocalDateTime.parse(date2);

        List<Report> mockReports = new ArrayList<>();
        when(reportsRepository.findAllByDateBetweenAndClientId(localDateTime1, localDateTime2, clientId)).thenReturn(mockReports);

        List<Report> reports = reportsService.getReportByDateAndClientId(date1, date2, clientId);

        verify(reportsRepository, times(1)).findAllByDateBetweenAndClientId(eq(localDateTime1), eq(localDateTime2), eq(clientId));
        assertEquals(mockReports, reports);
    }

    @Test
    public void testGetReportByDateAndClientIdInvalidDates() {
        String date1 = "invalid_date";
        String date2 = "2023-09-30T23:59:59";
        Integer clientId = 1;

        assertThrows(ExceptionChallenge.class, () -> reportsService.getReportByDateAndClientId(date1, date2, clientId));

        verify(reportsRepository, never()).findAllByDateBetweenAndClientId(any(), any(), anyInt());
    }

    @Test
    public void testCreateCreditReport() {
        Account account = new Account();
        account.setAccountNumber(1);
        Client client = new Client();
        client.setName("Test Client");
        client.setIdentification(123);
        account.setClient(client);
        Movement movement = new Movement();
        movement.setDate(LocalDateTime.now());
        movement.setValue(100.0);
        double newBalance = 600.0;
        double initialBalance = 500.0;

        when(clientService.getClient(123)).thenReturn(client);

        reportsService.createCreditReport(account, movement, newBalance, initialBalance);

        verify(reportsRepository, times(1)).save(any(Report.class));
    }

    @Test
    public void testCreateDebitReport() {
        Account account = new Account();
        account.setAccountNumber(1);
        Client client = new Client();
        client.setName("Test Client");
        client.setIdentification(123);
        account.setClient(client);
        Movement movement = new Movement();
        movement.setDate(LocalDateTime.now());
        movement.setValue(100.0);
        double newBalance = 400.0;
        double initialBalance = 500.0;

        when(clientService.getClient(123)).thenReturn(client);

        reportsService.createDebitReport(account, movement, newBalance, initialBalance);

        verify(reportsRepository, times(1)).save(any(Report.class));
    }
}