package com.bankstatementprocessor.application.model;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Rahul
 * 		   This model object represents is used for converting view parameter type to case insensitive
 */

@Component
public class ViewParameterTypeConvertor implements Converter<String, ViewParameterType> {

    @Override
    public ViewParameterType convert(String value) {
        return ViewParameterType.fromString(value);
    }
}
