package edu.cnm.deepdive.interviewprep.controller;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResponseMapping {

  @ExceptionHandler(NoSuchElementException.class) //Nosuch element exception with associated with this status
  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Resource not found")
  public void notFound() {
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid request content")//comes back with 400
  public void badRequest() {
  }

}
