package com.springpageable.validator;

import com.springpageable.dto.DateRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompareDateClassAnnotationValidator
    implements ConstraintValidator<CompareDateClassAnnotation, DateRequestDTO> {

  @Override
  public boolean isValid(DateRequestDTO dateRequestDto, ConstraintValidatorContext context) {
    return !(dateRequestDto.getFromDate() == null
        || dateRequestDto.getToDate() == null
        || dateRequestDto.getFromDate().isAfter(dateRequestDto.getToDate()));
  }
}
