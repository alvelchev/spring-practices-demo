package com.springpracticesdemo.validator;

import com.springpracticesdemo.dto.DateRequestDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CompareDateClassAnnotationValidator
        implements ConstraintValidator<CompareDateClassAnnotation, DateRequestDTO> {

    @Override
    public boolean isValid(DateRequestDTO dateRequestDto, ConstraintValidatorContext context) {
        return !(dateRequestDto.getFromDate() == null
                || dateRequestDto.getToDate() == null
                || dateRequestDto.getFromDate().isAfter(dateRequestDto.getToDate()));
    }
}
