package com.springpageable.mapper;

import com.springpageable.dto.GetFutureDeviceResponseDTO;
import com.springpageable.model.FutureDevice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FutureDeviceMapper {

  @Mapping(target = "productId", source = "futureDevice.productId")
  GetFutureDeviceResponseDTO futureDeviceToFutureDeviceResponseDTO(FutureDevice futureDevice);
}
