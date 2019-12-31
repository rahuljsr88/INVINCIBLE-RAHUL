package com.bankstatementprocessor.application.exception;


public class ParsingNotAvailableException extends Exception{
    private String description;
    public ParsingNotAvailableException(String description) {
        this.description = description;
    }
}
