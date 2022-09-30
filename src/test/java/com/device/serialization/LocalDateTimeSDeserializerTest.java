package com.device.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.springpageable.serialization.LocalDateTimeSDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocalDateTimeSDeserializerTest {

    @Mock
    private JsonParser jsonParser;

    @Mock
    private DeserializationContext deserializationContext;

    private LocalDateTimeSDeserializer localDateTimeSDeserializer;

    @BeforeEach
    void setUp() {
        localDateTimeSDeserializer = new LocalDateTimeSDeserializer();
    }

    @Test
    void testThat_Deserializes_ReturnsExpectedDateTime() throws Exception {
        LocalDateTime expectedDateTime = LocalDateTime.now();
        expectedDateTime = expectedDateTime.minusNanos(expectedDateTime.getNano());
        when(jsonParser.getText()).thenReturn(String.valueOf(expectedDateTime.toInstant(ZoneOffset.UTC).getEpochSecond()));
        LocalDateTime actualDateTime = localDateTimeSDeserializer.deserialize(jsonParser, deserializationContext);

        assertEquals(expectedDateTime, actualDateTime);
    }
}
