package com.challenge.challenge.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.challenge.challenge.model.ErrorResponse;

@RestControllerAdvice
public class HandleException {

    @ExceptionHandler(value = {ExceptionChallenge.class})
    public ResponseEntity<ErrorResponse> handleException(ExceptionChallenge ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }
}
