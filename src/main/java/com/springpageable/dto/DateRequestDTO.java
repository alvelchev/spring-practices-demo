package com.springpageable.dto;

import com.springpageable.validator.CompareDateClassAnnotation;
import com.springpageable.validator.DateFieldAnnotation;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@CompareDateClassAnnotation
public class DateRequestDTO {
  @NotNull LocalDate fromDate;
  @Future @DateFieldAnnotation LocalDate toDate;
}
