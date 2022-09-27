package com.springpageable.service;

import com.springpageable.dto.GetFutureDeviceResponseDTO;
import com.springpageable.mapper.FutureDeviceMapper;
import com.springpageable.repository.FutureDeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class FutureDeviceService {

  private FutureDeviceRepository futureDeviceRepository;

  private final FutureDeviceMapper futureDeviceMapper;

  @Autowired
  public FutureDeviceService(
      FutureDeviceRepository futureDeviceRepository, FutureDeviceMapper futureDeviceMapper) {
    this.futureDeviceRepository = futureDeviceRepository;
    this.futureDeviceMapper = futureDeviceMapper;
  }

  /**
   * Find all future devices
   *
   * @param p - pagination object containing page size, page number and sort parameters
   * @param searchParameter - term to search by serialNumber, productId or customerName
   * @return Page of {@link GetFutureDeviceResponseDTO}
   */
  public Page<GetFutureDeviceResponseDTO> retrieveFutureDevices(
      Pageable p, String searchParameter) {
    var futureDevices = futureDeviceRepository.findAll();
    var devices =
        futureDevices.stream()
            .map(futureDeviceMapper::futureDeviceToFutureDeviceResponseDTO)
            .collect(Collectors.toList());
    return new PageImpl<>(devices, p, devices.size());
  }
}
