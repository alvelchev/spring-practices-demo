package com.springpageable.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SwaggerHealthStatusDetailsDTO {

    @Schema(example = "0")
    private int id;
    @Schema(example = "Alright")
    private String name;
}
