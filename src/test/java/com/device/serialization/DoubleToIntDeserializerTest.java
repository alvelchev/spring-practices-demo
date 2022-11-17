package com.device.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.springpageable.enums.Origin;
import com.springpageable.serialization.DoubleToIntDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DoubleToIntDeserializerTest {
  private DoubleToIntDeserializer underTest;

  @Mock private JsonParser jsonParser;

  @Mock private DeserializationContext deserializationContext;

  @BeforeEach
  void setUp() {
    underTest = new DoubleToIntDeserializer();
  }

  @Test
  void testThat_Deserialize_ReturnsExpectedResult() throws Exception {
    // Act
    var result = underTest.deserialize(jsonParser, deserializationContext);

    // Assert
    assertEquals(Origin.PM_MANUAL_UPLOAD, result);
  }
}
