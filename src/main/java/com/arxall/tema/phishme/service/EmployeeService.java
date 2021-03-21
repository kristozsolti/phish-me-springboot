package com.arxall.tema.phishme.service;

import com.arxall.tema.phishme.exception.ResourceNotFoundException;
import com.arxall.tema.phishme.model.Employee;
import com.arxall.tema.phishme.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Get all employees from the database.
     * @return : list of found employees.
     * This could be improved with pagination functionality.
     */
    public List<Employee> getAllEmployees() {
        log.info("Get all employees");
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(String employeeId) {
        Optional < Employee > employeeOptional = employeeRepository.findById(employeeId);

        if (employeeOptional.isPresent()) {
            log.info("Get employee with id " + employeeId);
            return employeeOptional.get();
        } else {
            log.error("Cannot get employee with id " + employeeId);
            throw new ResourceNotFoundException("Record not found with id : " + employeeId);
        }
    }

    public Employee addEmployee(Employee employee) {
        log.info("Saving employee " + employee);
        return employeeRepository.save(employee);
    }

    /**
     * Update functionality that updates an existing employee in the database, if exists.
     * @param employee : employee that will be updated.
     * @return the updated employee object
     * This could be improved with either Mapstruct library or by
     * using DAO - DTO converters that would result on cleaner code.
     */
    public Employee updateEmployee(Employee employee) {
        Optional < Employee > employeeOptional = employeeRepository.findById(employee.getId());

        if (employeeOptional.isPresent()) {
            Employee employeeUpdate = employeeOptional.get();
            employeeUpdate.setId(employee.getId());
            employeeUpdate.setName(employee.getName());
            employeeUpdate.setEmail(employee.getEmail());
            employeeUpdate.setJobTitle(employee.getJobTitle());
            employeeUpdate.setImageUrl(employee.getImageUrl());

            log.info("Update employee " + employee);
            employeeRepository.save(employeeUpdate);
            return employeeUpdate;
        } else {
            log.error("Cannot update employee - Does not exists in database. ");
            throw new ResourceNotFoundException("Record not found with id : " + employee.getId());
        }
    }

    public Employee deleteEmployee(String employeeId) {
        Optional < Employee > employeeOptional = employeeRepository.findById(employeeId);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            log.info("Deleting employee with id " + employeeId);
            employeeRepository.delete(employeeOptional.get());
            return employee;
        } else {
            log.error("Cannot delete employee - Does not exists in database. ");
            throw new ResourceNotFoundException("Record not found with id : " + employeeId);
        }
    }

    /**
     * This function is used to increment the phishing count field of an employee.
     * Used when they click on a phishing link.
     * @param employeeId : id of the employee that clicked to the phishing link.
     */
    public void recordPhishedEmployee(String employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.incrementPhishingCount();
            employeeRepository.save(employee);
        }
    }
}
