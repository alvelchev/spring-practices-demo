package com.device.validator;

import com.springpageable.validator.DateFieldValidator;
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
class DateFieldValidatorTest {
  @InjectMocks private DateFieldValidator validator;

  @Mock private ConstraintValidatorContext mockConstraintValidatorContext;

  @Test
  void testThat_validator_willPass() {
    boolean result = validator.isValid(LocalDate.now(), mockConstraintValidatorContext);
    assertTrue(result);
  }

  @Test
  void testThat_validator_willFailed_inCase_dateRange_exceed() {
    boolean result =
        validator.isValid(LocalDate.now().plusDays(30), mockConstraintValidatorContext);
    assertFalse(result);
  }

  @Test
  void testThat_validator_willFailed_inCase_dateIsNull() {
    boolean result = validator.isValid(null, mockConstraintValidatorContext);
    assertFalse(result);
  }
}
