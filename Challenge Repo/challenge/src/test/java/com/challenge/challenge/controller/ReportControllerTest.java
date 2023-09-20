package com.challenge.challenge.controller;

import com.challenge.challenge.model.Report;
import com.challenge.challenge.service.ReportsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReportControllerTest {

    @InjectMocks
    private ReportController reportController;

    @Mock
    private ReportsService reportsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReportByDateAndClientId() {
        String date1 = "2023-01-01";
        String date2 = "2023-02-01";
        Integer clientId = 1;

        List<Report> expectedReports = new ArrayList<>();
        // Agregar los informes esperados a la lista

        when(reportsService.getReportByDateAndClientId(date1, date2, clientId)).thenReturn(expectedReports);

        List<Report> retrievedReports = reportController.getMovement(date1, date2, clientId);

        assertEquals(expectedReports, retrievedReports);
    }
}