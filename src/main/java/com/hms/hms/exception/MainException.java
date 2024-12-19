package com.hms.hms.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MainException extends Exception{
  private String message;
  private Integer statusCode;
  public MainException(Integer statusCode , String message){
    super(message);
    this.message = message;
    this.statusCode =statusCode;
  }
}
