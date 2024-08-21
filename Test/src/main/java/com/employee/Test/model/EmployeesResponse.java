package com.employee.Test.model;

import java.util.List;

import lombok.Data;

@Data
public class EmployeesResponse {
    private String status;
    private Integer statusCode;
    private List<Employee> data;
    private String message;
}
