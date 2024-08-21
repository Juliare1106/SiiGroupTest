package com.employee.Test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class Employee {

    private int id;
    @JsonProperty("employee_name")    
    private String employeeName;
    @JsonProperty("employee_salary")
    private double employeeSalary;
    @JsonProperty("employee_age")
    private int employeeAge;
    @JsonProperty("profile_image")
    private String profileImage;
    private double annualSalary; 
}
