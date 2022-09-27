package com.springpageable.service;

import com.springpageable.dto.GetFutureDeviceResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class FutureDeviceService {

  /**
   * Find all future devices
   *
   * @param p - pagination object containing page size, page number and sort parameters
   * @param searchParameter - term to search by serialNumber, productId or customerName
   * @return Page of {@link GetFutureDeviceResponseDTO}
   */
  public Page<GetFutureDeviceResponseDTO> retrieveFutureDevices(
      Pageable p, String searchParameter) {
    ArrayList<GetFutureDeviceResponseDTO> getFutureDeviceResponseDTOS =
        new ArrayList<GetFutureDeviceResponseDTO>();
    var getFutureDeviceResponseDTO = new GetFutureDeviceResponseDTO();
    getFutureDeviceResponseDTO.setCustomer("test");
    getFutureDeviceResponseDTO.setId(1L);
    getFutureDeviceResponseDTO.setProductId("8719030");
    getFutureDeviceResponseDTO.setSerialNumber("testSerialNumber");
    getFutureDeviceResponseDTOS.add(getFutureDeviceResponseDTO);
    return new PageImpl<>(getFutureDeviceResponseDTOS, p, getFutureDeviceResponseDTOS.size());
  }
}
