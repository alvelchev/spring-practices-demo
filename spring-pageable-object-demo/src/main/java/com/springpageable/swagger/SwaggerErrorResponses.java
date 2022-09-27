package com.springpageable.swagger;

import com.springpageable.dto.ErrorDetailsDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(
    responseCode = "400",
    content =
        @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorDetailsDTO.class)))
@ApiResponse(
    responseCode = "401",
    content =
        @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorDetailsDTO.class)))
@ApiResponse(
    responseCode = "403",
    content =
        @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorDetailsDTO.class)))
@ApiResponse(
    responseCode = "404",
    content =
        @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorDetailsDTO.class)))
@ApiResponse(
    responseCode = "409",
    content =
        @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorDetailsDTO.class)))
@ApiResponse(
    responseCode = "500",
    content =
        @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorDetailsDTO.class)))
public @interface SwaggerErrorResponses {}
