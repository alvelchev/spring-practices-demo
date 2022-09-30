package com.device.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.springpageable.serialization.LocalDateSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocalDateSerializerTest {

    private LocalDateSerializer localDateSerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    private final ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);

    @BeforeEach
    void setUp() {
        localDateSerializer = new LocalDateSerializer();
    }

    @Test
    void testThat_Serialize_GeneratesTimestamp() throws Exception {
        LocalDate dateUnderTest = LocalDate.of(2020, Month.SEPTEMBER, 25);

        Long expectedResult = dateUnderTest.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();

        localDateSerializer.serialize(dateUnderTest, jsonGenerator, mock(SerializerProvider.class));

        verify(jsonGenerator, times(1)).writeNumber(argumentCaptor.capture());
        verify(jsonGenerator, times(0)).writeNull();

        Long actualResult = argumentCaptor.getValue();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testThat_Serialize_GeneratesNullIfTheLocalDateIsNull() throws Exception {
        localDateSerializer.serialize(null, jsonGenerator, mock(SerializerProvider.class));

        verify(jsonGenerator, times(0)).writeNumber(any(Long.class));
        verify(jsonGenerator, times(1)).writeNull();
    }
}
