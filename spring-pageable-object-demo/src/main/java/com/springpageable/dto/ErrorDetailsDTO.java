package com.springpageable.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetailsDTO {

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime timestamp;

  private String message;
  private String description;
  private String errorCode;

  public ErrorDetailsDTO(LocalDateTime timestamp, String message, String description) {
    this.timestamp = timestamp;
    this.message = message;
    this.description = description;
  }
}
