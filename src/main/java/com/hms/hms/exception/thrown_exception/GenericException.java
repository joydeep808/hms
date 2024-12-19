package com.hms.hms.exception.thrown_exception;

import com.hms.hms.exception.MainException;

public class GenericException extends MainException{
  
  public GenericException(Integer statusCode , String message){
    super(statusCode, message);
  }
}
