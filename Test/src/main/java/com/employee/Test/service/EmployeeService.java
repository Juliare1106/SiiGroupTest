package com.employee.Test.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.employee.Test.model.EmployeeResponse;
import com.employee.Test.model.EmployeesResponse;

@Service
public class EmployeeService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://dummy.restapiexample.com/api/v1";

    public EmployeesResponse getAllEmployees() {
        try {
            EmployeesResponse response = restTemplate.getForObject(BASE_URL + "/employees", EmployeesResponse.class);
            return response != null ? response : null;
        } catch (HttpClientErrorException e) {
            System.out.println("API says " + e.getStatusCode().value());
            System.out.println("Error fetching employees: " + e.getMessage());
            System.out.println(BASE_URL + "/employees");
            EmployeesResponse response = new EmployeesResponse();
            response.setStatusCode(e.getStatusCode().value());
            return response;  //Return status code message from Dummy API
        }
    }

    public EmployeeResponse getEmployeeById(int id) {
        try {
            EmployeeResponse response = restTemplate.getForObject(BASE_URL + "/employee/" + id, EmployeeResponse.class);
            return response != null ? response : null;
        } catch (HttpClientErrorException e) {
            System.out.println("API says " + e.getStatusCode().value());
            System.out.println("Error fetching employee with ID " + id + ": " + e.getMessage());
            EmployeeResponse response = new EmployeeResponse();
            response.setStatusCode(e.getStatusCode().value());
            return response; //Return status code message from Dummy API
        }
    }
}