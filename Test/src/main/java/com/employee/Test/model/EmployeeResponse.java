package com.employee.Test.model;

import lombok.Data;

@Data
public class EmployeeResponse {
    private String status;
    private Employee data;
    private String message;
}