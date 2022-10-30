package com.springpageable.dto;

import com.springpageable.validator.CompareDateClassAnnotation;
import com.springpageable.validator.DateFieldAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CompareDateClassAnnotation
@Valid
public class DateRequestDTO {
  @NotNull
  private LocalDate fromDate;

  @Future
  @DateFieldAnnotation
  private LocalDate toDate;
}
