package com.springpageable.swagger;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Parameters({
  @Parameter(
      in = ParameterIn.QUERY,
      description = "Sorting criteria by multiple fields.",
      name = "sort",
      array = @ArraySchema(schema = @Schema(type = "string"))),
  @Parameter(
      in = ParameterIn.QUERY,
      description = "Zero-based page index (0..N)",
      name = "page",
      content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
  @Parameter(
      in = ParameterIn.QUERY,
      description = "The size of the page to be returned",
      name = "size",
      content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
  @Parameter(in = ParameterIn.QUERY, name = "p", hidden = true)
})
public @interface SwaggerPageable {}
