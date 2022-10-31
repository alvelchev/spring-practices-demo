package com.device.validator;

import com.springpageable.dto.DateRequestDTO;
import com.springpageable.validator.CompareDateClassAnnotationValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CompareDateClassAnnotationValidatorTest {

  @InjectMocks private CompareDateClassAnnotationValidator validator;

  @Mock private ConstraintValidatorContext mockConstraintValidatorContext;

  @Test
  void testThat_validator_willPass() {
    DateRequestDTO dateRequestDTO =
        new DateRequestDTO(LocalDate.now(), LocalDate.now().plusDays(1));
    boolean result = validator.isValid(dateRequestDTO, mockConstraintValidatorContext);
    assertTrue(result);
  }

  @Test
  void testThat_validator_willFailed_inCase_fromDateIsAfterToDate() {
    DateRequestDTO dateRequestDTO =
        new DateRequestDTO(LocalDate.now().plusDays(2), LocalDate.now().plusDays(1));
    boolean result = validator.isValid(dateRequestDTO, mockConstraintValidatorContext);
    assertFalse(result);
  }

  @Test
  void testThat_validator_willFailed_inCase_fromDateIsNull() {
    DateRequestDTO dateRequestDTO = new DateRequestDTO(null, LocalDate.now().plusDays(1));
    boolean result = validator.isValid(dateRequestDTO, mockConstraintValidatorContext);
    assertFalse(result);
  }

  @Test
  void testThat_validator_willFailed_inCase_toDateIsNull() {
    DateRequestDTO dateRequestDTO = new DateRequestDTO(null, LocalDate.now().plusDays(1));
    boolean result = validator.isValid(dateRequestDTO, mockConstraintValidatorContext);
    assertFalse(result);
  }
}
