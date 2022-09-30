package com.device.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.springpageable.exception.BadRequestException;
import com.springpageable.serialization.LocalDateDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocalDateDeserializerTest {

    @Mock
    private JsonParser jsonParser;

    @Mock
    private DeserializationContext deserializationContext;

    private LocalDateDeserializer localDateDeserializer;

    @BeforeEach
    void setUp() {
        localDateDeserializer = new LocalDateDeserializer();
    }

    @Test
    void testThat_Deserialize_ReturnsExpectedDate() throws Exception {
        when(jsonParser.getText()).thenReturn("2020-09-16");

        var expectedDate = LocalDate.of(2020, Month.SEPTEMBER, 16);

        var actualDate = localDateDeserializer.deserialize(jsonParser, deserializationContext);

        assertEquals(expectedDate, actualDate);
    }

    @Test
    void testThat_Deserialize_ThrowsBadRequestException_WhenDateIsNull() throws Exception {
        var thrown = assertBadRequestExceptionForInvalidDate(null);

        assertEquals("Invalid format of null: [null]", thrown.getMessage());
    }

    @Test
    void testThat_Deserialize_ThrowsBadRequestException_WhenDateIsIncorrect() throws Exception {
        var thrown = assertBadRequestExceptionForInvalidDate("0000-00-00");

        assertEquals("Text '0000-00-00' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 0",
                thrown.getMessage());
    }

    @Test
    void testThat_Deserialize_ThrowsBadRequestException_WhenDateIsTooShort() throws Exception {
        var thrown = assertBadRequestExceptionForInvalidDate("12-12-22");

        assertEquals("Invalid format of null: [12-12-22]", thrown.getMessage());
    }

    private Exception assertBadRequestExceptionForInvalidDate(String o) throws IOException {
        when(jsonParser.getText()).thenReturn(o);
        return assertThrows(BadRequestException.class,
                () -> localDateDeserializer.deserialize(jsonParser, deserializationContext));
    }
}
