package com.hyper.backend.advice;

import com.hyper.backend.exception.IllegalOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class IllegalOperationAdvice {

  @ResponseBody
  @ExceptionHandler(IllegalOperationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String illegalOperationHandler(IllegalOperationException ex) {
    return ex.getMessage();
  }
}