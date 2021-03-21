package com.arxall.tema.phishme.controller;

import com.arxall.tema.phishme.model.Employee;
import com.arxall.tema.phishme.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/top-ten-phished")
    public ResponseEntity<List<Employee>> topPhishedEmployees() {
        List<Employee> employees = this.dashboardService.topPhishedEmployees();
        return new ResponseEntity(employees, HttpStatus.OK);
    }

}
