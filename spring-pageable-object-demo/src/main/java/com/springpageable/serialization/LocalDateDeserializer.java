package com.springpageable.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.springpageable.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

  public static final int MINIMUM_DATE_LENGTH = 10;

  public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    String date = p.getText();

    if (date != null && date.length() >= MINIMUM_DATE_LENGTH) {
      try {
        return LocalDate.parse(date.substring(0, MINIMUM_DATE_LENGTH));
      } catch (Exception e) {
        log.error("{} - {}", p.getCurrentName(), e.getMessage(), e);

        throw new BadRequestException(e.getMessage());
      }
    }

    throw new BadRequestException(
        String.format("Invalid format of %s: [%s]", p.getCurrentName(), date));
  }
}
