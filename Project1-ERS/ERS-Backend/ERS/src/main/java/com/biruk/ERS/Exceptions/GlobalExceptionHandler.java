package com.biruk.ERS.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

//✅ This prevents 500 errors and returns a meaningful 404 response.
//✅ Now the API behaves like a well-designed RESTful service.
//✅ Your users (or front-end) can easily understand the error and handle it properly.


    //handles Illegal Argument Exception (for Auth purpose)

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity <Map<String,Object>> handleIllegalArgumentException (IllegalArgumentException e){

        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("timeStamp", System.currentTimeMillis());
        errorResponse.put("status", HttpStatus.UNAUTHORIZED.value());
        errorResponse.put("error", "Not Found");
        errorResponse.put("message", e.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
    }

    //handles No Such Element Exception (when reimbursement claim or user is not found).

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchElementException (NoSuchElementException e){
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("timeStamp", System.currentTimeMillis());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error", "Not Found");
        errorResponse.put("message", e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


}
