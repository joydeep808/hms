package com.hms.hms.exception.thrown_exception;

import com.hms.hms.exception.MainException;

public class AlreadyExistException extends MainException {
  public AlreadyExistException( String message){
    super(400, message);
  }
  
}
