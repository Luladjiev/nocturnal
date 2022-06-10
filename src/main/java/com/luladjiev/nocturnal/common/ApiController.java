package com.luladjiev.nocturnal.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Matches all unhandled API requests and returns JSON with 404 message
 */
@RestController
public class ApiController {

  @RequestMapping("/api/**")
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorResponse apiHandler() {
    return new ErrorResponse("Resource not found", HttpStatus.NOT_FOUND);
  }
}
