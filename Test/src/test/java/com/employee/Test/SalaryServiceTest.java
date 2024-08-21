package com.employee.Test;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import com.employee.Test.service.SalaryService;

public class SalaryServiceTest {

    private final SalaryService salaryService = new SalaryService();

    @Test
    void testComputeAnnualSalary() {
        double monthlySalary = 5000.0;
        double annualSalary = salaryService.computeAnnualSalary(monthlySalary);
        assertThat(annualSalary).isEqualTo(60000.0);
    }
}
