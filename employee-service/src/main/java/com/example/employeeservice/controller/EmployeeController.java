package com.example.employeeservice.controller;

import com.example.employeeservice.dto.EmployeeDetails;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    EmployeeDetailsService employeeDetailsService;

    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/department/{id}")
    public List<EmployeeDetails> getEmployeeDetailsByDepartmentId(@PathVariable("id") int id) {
        List<EmployeeDetails> employeesDetails = employeeDetailsService.getEmployeeDetailsByDepartmentId(id);
        return employeesDetails;
    }

}
