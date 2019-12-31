package com.bankstatementprocessor.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
/**
 * @author Rahul
 * 		   This is the generic exception handler for intercepting and handling the exceptions in the rest controller
 */

@ControllerAdvice
public class ExceptionHandler {
    private static final String GENERIC_ERROR_MESSAGE = "Internal Server error caused by:";
    private static final String INVALID_REQUEST_ERROR_MESSAGE = "Invalid requests error caused by:";
    private static final String REPORT_NOT_FOUND_ERROR = "Report not found error";


    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<?> handleInvalidReqhestException(InvalidRequestException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(INVALID_REQUEST_ERROR_MESSAGE + exception.getDescription());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(INVALID_REQUEST_ERROR_MESSAGE + exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<?> handleIllegalArgumentException(NoReportFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(REPORT_NOT_FOUND_ERROR + exception.getDescription());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<?> unhandledException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GENERIC_ERROR_MESSAGE + exception.getMessage());
    }
}
