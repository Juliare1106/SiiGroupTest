package com.employee.Test.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.Test.model.Employee;
import com.employee.Test.model.EmployeeResponse;
import com.employee.Test.model.EmployeesResponse;
import com.employee.Test.service.EmployeeService;
import com.employee.Test.service.ErrorResponseService;
import com.employee.Test.service.SalaryService;

@CrossOrigin(origins = "http://localhost:3000") // GP. Cross origin request from specific origin (React frontEnd)
@Controller
public class EmployeeController {
    /*
     * EmployeeController has a single responsibility: handling HTTP requests
     * related to employees. S
     * The controller is closed for modification but open for extension. You're able
     * to modify the services instead the controller. O
     * EmployeeService or SalaryService are usable without altering the correctness
     * of the EmployeeController. L
     * The services provide specific methods relevant to their respective concerns.
     * I
     * The EmployeeController depends on abstractions (Services). D
     */
    @Autowired // Dependendy Injection
    private EmployeeService employeeService;
    @Autowired // Dependendy Injection
    private SalaryService salaryService;
    @Autowired // Dependendy Injection
    private ErrorResponseService errorResponseService;

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        EmployeesResponse employeesResponse = employeeService.getAllEmployees();
        List<Employee> employees = employeesResponse.getData() != null ? employeesResponse.getData()
                : Collections.emptyList();
        if (!employees.isEmpty()) {
            for (Employee obj : employees) {
                obj.setAnnualSalary(salaryService.computeAnnualSalary(obj.getEmployeeSalary())); //Annual salary compute
            }
            return ResponseEntity.ok(employees);
        } else {
            System.out.println("API says "+employeesResponse.getStatusCode());
            return errorResponseService.handleErrorResponse(employeesResponse.getStatusCode(), 2); //Response handle
        }
    }

    @GetMapping("/employee")
    public ResponseEntity<?> getEmployeeById(@RequestParam("id") int id) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
        if (employeeResponse.getData() != null) {
            Employee employee = employeeResponse.getData();
            employee.setAnnualSalary(salaryService.computeAnnualSalary(employee.getEmployeeSalary())); //Annual salary compute
            return ResponseEntity.ok(employee);
        } else {
            System.out.println("API says "+employeeResponse.getStatusCode());
            return errorResponseService.handleErrorResponse(employeeResponse.getStatusCode(), 1); //Response handle
        }
    }
}