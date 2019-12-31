package com.bankstatementprocessor.application.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidRequestException extends IllegalArgumentException{
    String description;
    public InvalidRequestException(String description) {
        this.description = description;
    }
}
