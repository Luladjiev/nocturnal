package com.luladjiev.nocturnal.common;

import org.springframework.http.HttpStatus;

record ErrorResponse(String message, HttpStatus status, Integer code) {}
