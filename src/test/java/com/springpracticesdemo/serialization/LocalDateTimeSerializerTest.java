package com.springpracticesdemo.serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@ExtendWith(MockitoExtension.class)
class LocalDateTimeSerializerTest {

    private LocalDateTimeSerializer localDateTimeSerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    private final ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);

    @BeforeEach
    void setUp() {
        localDateTimeSerializer = new LocalDateTimeSerializer();
    }

    @Test
    void testThat_Serialize_GeneratesTimestamp() throws Exception {
        LocalDateTime dateUnderTest = LocalDateTime.of(
                2020, Month.SEPTEMBER, 25, 14, 30, 40);

        Long expectedResult = dateUnderTest.toInstant(ZoneOffset.UTC).toEpochMilli();

        localDateTimeSerializer.serialize(dateUnderTest, jsonGenerator, mock(SerializerProvider.class));

        verify(jsonGenerator).writeNumber(argumentCaptor.capture());
        verify(jsonGenerator, times(0)).writeNull();

        Long actualResult = argumentCaptor.getValue();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testThat_Serialize_GeneratesNullIfTheLocalDateTimeIsNull() throws Exception {
        localDateTimeSerializer.serialize(null, jsonGenerator, mock(SerializerProvider.class));

        verify(jsonGenerator, times(0)).writeNumber(any(Long.class));
        verify(jsonGenerator).writeNull();
    }
}
