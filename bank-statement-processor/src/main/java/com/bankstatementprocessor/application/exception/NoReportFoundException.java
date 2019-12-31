package com.bankstatementprocessor.application.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoReportFoundException extends RuntimeException {
    private String description;
    public NoReportFoundException(String description) {
        this.description = description;
    }
}
