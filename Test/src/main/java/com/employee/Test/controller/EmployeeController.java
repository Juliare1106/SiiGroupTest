package com.employee.Test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.Test.model.Employee;
import com.employee.Test.service.EmployeeService;
import com.employee.Test.service.SalaryService;

@CrossOrigin(origins = "http://localhost:3000") // GP. Cross origin request from specific origin (React frontEnd)
@Controller
public class EmployeeController { 
    /*  EmployeeController has a single responsibility: handling HTTP requests related to employees. S
    The controller is closed for modification but open for extension. You're able to modify the services instead the controller. O
    EmployeeService or SalaryService are usable without altering the correctness of the EmployeeController. L
    The services provide specific methods relevant to their respective concerns. I
    The EmployeeController depends on abstractions (Services). D
    */
    @Autowired //Dependendy Injection
    private EmployeeService employeeService; 
    @Autowired //Dependendy Injection
    private SalaryService salaryService;
    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        if (!employees.isEmpty()) {
            for (Employee obj : employees) {
                obj.setAnnualSalary(salaryService.computeAnnualSalary(obj.getEmployeeSalary()));
            }
            return ResponseEntity.ok(employees);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", "Employees not found"); //GP. Error Handling
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse); //GP. ResponseEntity Controls response and body for different returns
        }
    }

    @GetMapping("/employee")
    public ResponseEntity<?> getEmployeeById(@RequestParam("id") int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            employee.setAnnualSalary(salaryService.computeAnnualSalary(employee.getEmployeeSalary()));
            return ResponseEntity.ok(employee);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", "Employee not found"); //GP. Error Handling
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse); //GP. ResponseEntity Controls response and body for different returns
        }
    }
}