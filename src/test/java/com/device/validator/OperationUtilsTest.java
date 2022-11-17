package com.device.validator;

import com.springpageable.utils.OperationUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class OperationUtilsTest {
  private OperationUtils underTest;

  @Test
  void testThat_validator_willFailed_inCase_dateRange_exceed() {
    // Arrange
    List<String> filteredPartNumber = Arrays.asList("testRoot1");

    // Act
    var result = underTest.getEqualRecordsFromTwoLists(filteredPartNumber);

    // Assert
    assertNotNull(result);
    assertEquals(1, result.size());
  }
}
