package com.hms.hms.exception.thrown_exception;

import com.hms.hms.exception.MainException;

public class NotFoundException extends MainException{
  public NotFoundException(String message){
    super(404, message);
  }
}
