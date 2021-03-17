package com.arxall.tema.phishme.service;

import com.arxall.tema.phishme.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(String employeeId);

    void deleteEmployee(String id);
}
