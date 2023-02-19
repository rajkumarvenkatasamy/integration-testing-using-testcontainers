package com.example.employeeservice.service;

import com.example.employeeservice.dto.Department;
import com.example.employeeservice.dto.EmployeeDetails;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeDetailsService {

    final EmployeeRepository employeeRepository;

    public EmployeeDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Value("${department.service.base.url}")
    private String departmentServiceBaseURL;

    public List<EmployeeDetails> getEmployeeDetailsByDepartmentId(int id){
        RestTemplate restTemplate = new RestTemplate();
        List<EmployeeDetails> employeesDetails = new ArrayList<>();
        int n ;

        List<Employee> employeeList = employeeRepository.findByDepartmentId(id);
        n = employeeList.size();

        String departmentRestEndpoint = departmentServiceBaseURL + "/department/"+id;
        Department department =
                restTemplate.getForObject(departmentRestEndpoint, Department.class);

        if (department != null) {
            for (int i = 0; i < n; i++) {
                EmployeeDetails employeeDepartment = new EmployeeDetails();
                employeeDepartment.setEmployeeId(employeeList.get(i).getId());
                employeeDepartment.setEmployeeName(employeeList.get(i).getName());
                employeeDepartment.setAge(employeeList.get(i).getAge());
                employeeDepartment.setPosition(employeeList.get(i).getPosition());
                employeeDepartment.setDepartmentId(department.getId());
                employeeDepartment.setDepartmentName(department.getName());
                employeesDetails.add(employeeDepartment);
            }
        }
        else {
            System.out.println("No department detail exists for the id : " + id);
        }

        return employeesDetails;

    }

}
