package com.springpracticesdemo.serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.springpracticesdemo.enums.Origin;

@ExtendWith(MockitoExtension.class)
class DoubleToIntDeserializerTest {
    private DoubleToIntDeserializer underTest;

    @Mock
    private JsonParser jsonParser;

    @Mock
    private DeserializationContext deserializationContext;

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
