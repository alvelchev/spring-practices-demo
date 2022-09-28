package com.springpageable.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GetFutureDeviceResponseDTO {

  @Schema(example = "1")
  private Long id;

  @Schema(example = "BRMSN01")
  private String serialNumber;

  @Schema(example = "8719030")
  private String productId;

  @Schema(example = "1")
  private Long customerId;
}
