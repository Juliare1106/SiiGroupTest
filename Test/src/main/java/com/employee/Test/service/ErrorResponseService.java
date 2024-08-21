package com.employee.Test.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ErrorResponseService {
        public ResponseEntity<?> handleErrorResponse(Integer code, Integer Type){ //Method for control error responses from Dummy API
        Map<String, String> errorResponse = new HashMap<>();
        if(null==code)
        {
            errorResponse.put("errorMessage", "Employee not found");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(errorResponse);
        }
        else switch (code) {
            case 429 -> {
                errorResponse.put("errorMessage", Type == 1 ? "429 Dummy API employee by ID is not avaliable. Try again." :
                "429 API Dummy is not avaliable for all employees. Try again."); //GP. Error Handling
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(errorResponse);
            }
            case 500 -> {
                errorResponse.put("errorMessage", "Dummy API Server Internal error"); //GP. Error Handling
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
            }
            case 404 -> {
                errorResponse.put("errorMessage", "Dummy API Not found"); //GP. Error Handling
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            default -> {
                errorResponse.put("errorMessage", "Employee not found"); //GP. Error Handling
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(errorResponse);
            }
        }
            
    }
}