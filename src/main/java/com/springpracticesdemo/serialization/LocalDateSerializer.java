package com.springpracticesdemo.serialization;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

    private static final long serialVersionUID = 710599115837608983L;

    public LocalDateSerializer() {
        super(LocalDate.class);
    }

    @Override
    public void serialize(
            final LocalDate value, final JsonGenerator generator, final SerializerProvider provider)
            throws IOException {
        if (value != null) {
            final long mills = value.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
            generator.writeNumber(mills);
        } else {
            generator.writeNull();
        }
    }
}
