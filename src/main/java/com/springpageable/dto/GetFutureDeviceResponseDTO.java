package com.springpageable.dto;

import com.springpageable.enums.HealthStatus;
import com.springpageable.swagger.SwaggerHealthStatusDetailsDTO;
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

  @Schema(implementation = SwaggerHealthStatusDetailsDTO.class)
  private HealthStatus.Details healthStatus;
}
