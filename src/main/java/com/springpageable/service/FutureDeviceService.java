package com.springpageable.service;

import com.springpageable.dto.FutureDeviceDTO;
import com.springpageable.dto.GetFutureDeviceResponseDTO;
import com.springpageable.exception.ConflictException;
import com.springpageable.exception.ResourceNotFoundException;
import com.springpageable.model.mapper.FutureDeviceMapper;
import com.springpageable.repository.FutureDeviceRepository;
import com.springpageable.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class FutureDeviceService {

  private final FutureDeviceRepository futureDeviceRepository;
  private final UserRepository userRepository;

  private final FutureDeviceMapper futureDeviceMapper;

  @Autowired
  public FutureDeviceService(
      FutureDeviceRepository futureDeviceRepository, FutureDeviceMapper futureDeviceMapper,UserRepository userRepository) {
    this.futureDeviceRepository = futureDeviceRepository;
    this.futureDeviceMapper = futureDeviceMapper;
    this.userRepository=userRepository;
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
    var futureDevices = futureDeviceRepository.findFutureDevices(p,searchParameter);
    if (futureDevices.isEmpty()) {
      return Page.empty();
    }
    var devices =
        futureDevices.stream()
            .map(futureDeviceMapper::futureDeviceToFutureDeviceResponseDTO)
            .collect(Collectors.toList());
    return new PageImpl<>(devices, p, devices.size());
  }

  /**
   * Creates new record in device future table, containing combination between
   * serialNumber,productId and customerId
   *
   * @param futureDeviceDTO - {@link FutureDeviceDTO} object containing the new data
   */
  public void createFutureDevice(FutureDeviceDTO futureDeviceDTO) {
    userRepository.findById(futureDeviceDTO.getCustomerId()).orElseThrow(() -> {
      String errMsg = String.format("There is no customer with id %s", futureDeviceDTO.getCustomerId());
      log.error(errMsg);
      throw new ResourceNotFoundException(errMsg);
    });
    var futureDevice = futureDeviceMapper.futureDeviceDTOToFutureDevice(futureDeviceDTO);
    try {
      futureDeviceRepository.save(futureDevice);
    } catch (DataIntegrityViolationException e) {
      String errMsg =
          String.format(
              "Combination with serial number %s,productId %s and customerId %d already exists",
              futureDeviceDTO.getSerialNumber(),
              futureDeviceDTO.getProductId(),
              futureDeviceDTO.getCustomerId());
      log.error(errMsg);
      throw new ConflictException(errMsg);
    }
  }

  /**
   * Deletes a future device
   *
   * @param id - id of the device to be deleted
   */
  public void deleteFutureDevice(long id) {
    var futureDevice =
        futureDeviceRepository
            .findById(id)
            .orElseThrow(
                () -> {
                  String errMsg = String.format("No future device found for id: %d", id);
                  log.error(errMsg);
                  throw new ResourceNotFoundException(errMsg);
                });
    futureDeviceRepository.deleteById(futureDevice.getId());
  }
}
