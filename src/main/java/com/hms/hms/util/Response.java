package com.hms.hms.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Response<T> {
  

  private String message;
  private Integer statusCode;
  private T data;
  private Boolean isSuccess;

  public static  ResponseEntity<Response<String>> sendSuccessResponse(String message) {
    Response<String> response = new Response<>();
    response.setMessage(message);
    response.setStatusCode(HttpStatus.OK.value());  // Status code for success (200 OK)
    response.setIsSuccess(true);
    return ResponseEntity.status(HttpStatus.OK).body(response);
    }
  
  public static <T> ResponseEntity<Response<T>> sendSuccessResponse(String message, T data) {
        Response<T> response = new Response<>();
        response.setMessage(message);
        response.setStatusCode(HttpStatus.OK.value());  // Status code for success (200 OK)
        response.setData(data);
        response.setIsSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    

    // Method to return an error response
    public static <T> ResponseEntity<Response<T>> sendErrorResponse(String message, Integer statusCode) {
        Response<T> response = new Response<>();
        response.setMessage(message);
        response.setStatusCode(statusCode);  // Use provided error code
        response.setData(null);
        response.setIsSuccess(false);

        return ResponseEntity.status(HttpStatus.valueOf(statusCode)).body(response);
    }
    
  
}
