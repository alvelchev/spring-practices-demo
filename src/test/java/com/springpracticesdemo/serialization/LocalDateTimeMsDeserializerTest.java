package com.springpracticesdemo.serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

@ExtendWith(MockitoExtension.class)
class LocalDateTimeMsDeserializerTest {

    @Mock
    private JsonParser jsonParser;

    @Mock
    private DeserializationContext deserializationContext;

    private LocalDateTimeMsDeserializer localDateTimeMsDeserializer;

    @BeforeEach
    void setUp() {
        localDateTimeMsDeserializer = new LocalDateTimeMsDeserializer();
    }

    @Test
    void testThat_Deserializes_ReturnsExpectedDateTime() throws Exception {
        LocalDateTime expectedDateTime = LocalDateTime.now();
        expectedDateTime = expectedDateTime.minusNanos(expectedDateTime.getNano());
        when(jsonParser.getText())
            .thenReturn(String.valueOf(expectedDateTime.toInstant(ZoneOffset.UTC).toEpochMilli()));
        LocalDateTime actualDateTime = localDateTimeMsDeserializer.deserialize(jsonParser, deserializationContext);

        assertEquals(expectedDateTime, actualDateTime);
    }
}
