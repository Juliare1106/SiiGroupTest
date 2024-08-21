package com.employee.Test.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.employee.Test.model.Employee;
import com.employee.Test.model.EmployeeResponse;
import com.employee.Test.model.EmployeesResponse;

@Service
public class EmployeeService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://dummy.restapiexample.com/api/v1";

    public List<Employee> getAllEmployees() {
        try {
            EmployeesResponse response = restTemplate.getForObject(BASE_URL + "/employees", EmployeesResponse.class);
            return response != null ? response.getData() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Error fetching employees: " + e.getMessage());
            System.out.println(BASE_URL + "/employees");
            return Collections.emptyList();  // Devuelve una lista vacía si ocurre un error
        }
    }

    public Employee getEmployeeById(int id) {
        try {
            EmployeeResponse response = restTemplate.getForObject(BASE_URL + "/employee/" + id, EmployeeResponse.class);
            return response != null ? response.getData() : null;
        } catch (HttpClientErrorException e) {
            System.out.println("Error fetching employee with ID " + id + ": " + e.getMessage());
            return null;  // Devuelve null si ocurre un error
        }
    }
}