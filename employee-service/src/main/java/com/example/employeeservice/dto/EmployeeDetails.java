package com.example.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetails {
    private Long employeeId;
    private String employeeName;
    private int age;
    private String position;
    private Long departmentId;
    private String departmentName;
}
