package com.luladjiev.nocturnal.utils;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController extends AbstractErrorController {

  public ErrorController(ErrorAttributes errorAttributes) {
    super(errorAttributes);
  }

  @RequestMapping("/error")
  public ResponseEntity<Object> handleError(HttpServletRequest request) {
    var path = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI).toString();

    if (path.startsWith("/api/")) {
      return ResponseEntity.status(404).body(new NotFoundDTO(404, "Resource not found"));
    }

    return ResponseEntity.ok()
        .header("Content-Type", "text/html")
        .body(new ClassPathResource("/public/index.html"));
  }
}
