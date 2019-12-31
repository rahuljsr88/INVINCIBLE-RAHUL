package com.bankstatementprocessor.application.model;

/**
 * @author Rahul
 * 		   This model object is used for the view request parameter used to filter the report
 */

public enum ViewParameterType {
    ALL, INVALID, VALID;

    public static ViewParameterType fromString(String key) {
        for (ViewParameterType type : ViewParameterType.values()) {
            if(type.name().equalsIgnoreCase(key)) {
                return type;
            }
            else
                throw new IllegalArgumentException("Invalid value for Report view parameter");
        }
        throw new IllegalArgumentException("Invalid value for Report view parameter");
    }
}
