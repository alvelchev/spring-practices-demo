package com.springpageable.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateFieldValidator implements ConstraintValidator<DateFieldAnnotation, LocalDate> {
    @Override
    public boolean isValid(final LocalDate date, final ConstraintValidatorContext context) {

        return !(date == null || date.isAfter(LocalDate.now().plusDays(28)));
    }
}
