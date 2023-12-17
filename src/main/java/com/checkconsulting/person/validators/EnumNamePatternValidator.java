package com.checkconsulting.person.validators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumNamePatternValidator implements ConstraintValidator<ValueOfEnum, Enum<?>> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }


    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
       if (value ==null) {
        return true;
       }
       return acceptedValues.contains(value.toString());
    }

}
