package com.banking.paypulse_banking.advisor;


import com.banking.paypulse_banking.Exception.DataIntegrityViolationException;
import com.banking.paypulse_banking.Exception.NotFoundException;
import com.banking.paypulse_banking.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, "error comming", e.getMessage()), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(409, " Data duplicate ", e.getMessage()), HttpStatus.BAD_REQUEST
        );
    }
}
