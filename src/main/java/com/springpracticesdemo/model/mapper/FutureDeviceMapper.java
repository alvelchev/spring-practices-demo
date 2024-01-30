package com.springpracticesdemo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.springpracticesdemo.dto.FutureDeviceDTO;
import com.springpracticesdemo.dto.GetFutureDeviceResponseDTO;
import com.springpracticesdemo.enums.HealthStatus;
import com.springpracticesdemo.model.FutureDevice;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", imports = { HealthStatus.class })
public interface FutureDeviceMapper {
    FutureDevice futureDeviceDTOToFutureDevice(FutureDeviceDTO futureDeviceDTO);

    @Mapping(target = "healthStatus", expression = "java(HealthStatus.getByValue(futureDevice.getHealthStatus()))")
    @Mapping(target = "productId", source = "futureDevice.productId")
    GetFutureDeviceResponseDTO futureDeviceToFutureDeviceResponseDTO(FutureDevice futureDevice);
}
