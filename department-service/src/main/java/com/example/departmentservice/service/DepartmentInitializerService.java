package com.example.departmentservice.service;

import com.example.departmentservice.model.Department;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DepartmentInitializerService {

    Map<Integer, Department> departments = new HashMap<>();

    public void initializeDepartmentData(){
        departments.put(1, new Department(1, "Engineering"));
        departments.put(2, new Department(2, "Accounts"));
        departments.put(3, new Department(3, "HR"));
        System.out.println("department details : " + departments);
    }

    public Map<Integer, Department> getDepartments() {
        return departments;
    }
}
