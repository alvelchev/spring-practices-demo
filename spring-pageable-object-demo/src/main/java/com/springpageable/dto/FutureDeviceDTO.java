package com.springpageable.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class FutureDeviceDTO {

  @Schema(example = "BRMSN01")
  @NotBlank
  private String serialNumber;

  @Schema(example = "8719030")
  @NotBlank
  private String productId;

  @Schema(example = "1")
  @NotNull
  @Positive
  private Long customerId;
}
