package com.springpageable.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SwaggerGetCountryResponseDTO {

  @Schema(example = "Bulgaria")
  private String name;

  @Schema(example = "BG")
  private String countryCode;
}
