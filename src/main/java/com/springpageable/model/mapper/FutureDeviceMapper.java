package com.springpageable.model.mapper;

import com.springpageable.dto.FutureDeviceDTO;
import com.springpageable.dto.GetFutureDeviceResponseDTO;
import com.springpageable.enums.HealthStatus;
import com.springpageable.model.FutureDevice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {HealthStatus.class})
public interface FutureDeviceMapper {
  FutureDevice futureDeviceDTOToFutureDevice(FutureDeviceDTO futureDeviceDTO);

  @Mapping(target = "healthStatus", expression = "java(HealthStatus.getByValue(futureDevice.getHealthStatus()))")
  @Mapping(target = "productId", source = "futureDevice.productId")
  GetFutureDeviceResponseDTO futureDeviceToFutureDeviceResponseDTO(FutureDevice futureDevice);
}
