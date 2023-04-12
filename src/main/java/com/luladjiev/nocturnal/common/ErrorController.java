package com.luladjiev.nocturnal.common;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class ErrorController extends AbstractErrorController {

  private final Logger logger = LoggerFactory.getLogger(ErrorController.class);

  public ErrorController(ErrorAttributes errorAttributes) {
    super(errorAttributes);
  }

  @RequestMapping("/error")
  public ResponseEntity<Object> handleError(
    HttpServletRequest httpServletRequest
  ) {
    var path = httpServletRequest
      .getAttribute(RequestDispatcher.ERROR_REQUEST_URI)
      .toString();

    if (!path.startsWith("/api/")) {
      return ResponseEntity
        .ok()
        .header("Content-Type", "text/html")
        .body(new ClassPathResource("/public/index.html"));
    }

    var error = httpServletRequest
      .getAttribute(RequestDispatcher.ERROR_MESSAGE)
      .toString();
    var status = (Integer) httpServletRequest.getAttribute(
      RequestDispatcher.ERROR_STATUS_CODE
    );

    logger.warn(String.format("[%s] %s: %s", status.toString(), path, error));

    return ResponseEntity
      .status(status)
      .body(new ErrorResponse(error, HttpStatus.valueOf(status)));
  }
}
