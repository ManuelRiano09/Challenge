package com.challenge.challenge.controller;

import com.challenge.challenge.model.Movement;
import com.challenge.challenge.model.Report;
import com.challenge.challenge.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportsService reportsService;

    @GetMapping("/{date1}/{date2}/{clientId}")
    public List<Report> getMovement(@PathVariable String date1 , @PathVariable String date2, @PathVariable Integer clientId) {
        return reportsService.getReportByDateAndClientId(date1, date2, clientId);
    }


}
