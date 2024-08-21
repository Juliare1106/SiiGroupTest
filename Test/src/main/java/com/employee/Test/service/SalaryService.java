package com.employee.Test.service;

import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    public double computeAnnualSalary(double monthlySalary) {
        return monthlySalary * 12;
    }
}
