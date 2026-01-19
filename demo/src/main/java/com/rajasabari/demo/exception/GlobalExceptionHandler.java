package com.rajasabari.demo.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handle(CustomException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
