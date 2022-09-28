package com.springpageable.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocalDateTimeMsDeserializer extends JsonDeserializer<LocalDateTime> {

  /**
   * Deserializes timestamp to LocalDateTime
   *
   * @param p - json parser with value
   * @param ctxt - context
   * @return parsed numerical string to LocalDateTime
   * @throws IOException when parsing fails
   */
  @Override
  public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return deserialize(Long.parseLong(p.getText()));
  }

  /**
   * Converts timestamp (in ms) long value to LocalDateTime
   *
   * @param milliseconds - long value in miliseconds of timestamp
   * @return converted to LocalDateTime value
   */
  public LocalDateTime deserialize(Long milliseconds) {
    return Instant.ofEpochMilli(milliseconds).atZone(ZoneOffset.UTC).toLocalDateTime();
  }
}
