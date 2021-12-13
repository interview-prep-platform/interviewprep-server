package edu.cnm.deepdive.interviewprep.controller;

import java.util.NoSuchElementException;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This is the class that REST communicates to JPA that an exception has occurred, returns a user
 * friendly status code in the header response.
 */
@RestControllerAdvice
@Profile("service")
public class ExceptionResponseMapping {

  /**
   * This method handles a no such element exception from the REST endpoint, and returns a
   * user-friendly status code in the header response.
   */
  @ExceptionHandler(NoSuchElementException.class)
  //Nosuch element exception with associated with this status
  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
  public void notFound() {
  }

  /**
   * This method handles an illegal argument exception from the rest endpoint and returns a
   * user-friendly status code in the header response.
   */
  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid request content")
//comes back with 400
  public void badRequest() {
  }

}
