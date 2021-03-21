package com.arxall.tema.phishme.service;

import com.arxall.tema.phishme.model.Employee;
import com.arxall.tema.phishme.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public DashboardService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Simple database query for the top 10 employees, ordered by phishing counting descending.
     * @return : list of top 10 phished employees.
     */
    public List<Employee> topPhishedEmployees() {
        return this.employeeRepository.findTop10ByOrderByPhishingCountDesc();
    }

}
