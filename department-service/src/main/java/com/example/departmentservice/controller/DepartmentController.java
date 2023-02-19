package com.example.departmentservice.controller;

import com.example.departmentservice.model.Department;
import com.example.departmentservice.service.DepartmentInitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DepartmentController {

    @Autowired
    DepartmentInitializerService departmentInitializerService;

    @GetMapping("/department/{id}")
    public Department getDepartmentDetailsById(@PathVariable("id") Integer id) {
        departmentInitializerService.initializeDepartmentData();
        return departmentInitializerService.getDepartments().get(id);
    }

}
