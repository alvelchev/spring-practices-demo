package com.springpageable.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GetUserResponseDTO {

    @Schema(example = "Bob")
    private String firstName;
    @Schema(example = "Marley")
    private String lastName;
    @Schema(example = "marlbojm")
    private String username;
    @Schema(example = "test.user@bbraun.com")
    private String email;
    @Schema(example = "true")
    @JsonProperty("isInternal")
    private boolean isInternal;
}
