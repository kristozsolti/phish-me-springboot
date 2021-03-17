package com.arxall.tema.phishme.service;

import com.arxall.tema.phishme.exception.ResourceNotFoundException;
import com.arxall.tema.phishme.model.Employee;
import com.arxall.tema.phishme.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        Optional < Employee > employeeDb = employeeRepository.findById(employeeId);

        if (employeeDb.isPresent()) {
            return employeeDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + employeeId);
        }
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Optional < Employee > employeeDb = employeeRepository.findById(employee.getId());

        if (employeeDb.isPresent()) {
            Employee employeeUpdate = employeeDb.get();
            employeeUpdate.setId(employee.getId());
            employeeUpdate.setName(employee.getName());
            employeeUpdate.setEmail(employee.getEmail());
            employeeUpdate.setJobTitle(employee.getJobTitle());
            employeeUpdate.setImageUrl(employee.getImageUrl());

            employeeRepository.save(employeeUpdate);
            return employeeUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + employee.getId());
        }
    }

    @Override
    public void deleteEmployee(String employeeId) {
        Optional < Employee > productDb = employeeRepository.findById(employeeId);

        if (productDb.isPresent()) {
            employeeRepository.delete(productDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + employeeId);
        }
    }
}
