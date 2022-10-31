package com.device.service;

import com.springpageable.dto.FutureDeviceDTO;
import com.springpageable.dto.GetFutureDeviceResponseDTO;
import com.springpageable.exception.BadRequestException;
import com.springpageable.exception.ConflictException;
import com.springpageable.exception.ResourceNotFoundException;
import com.springpageable.model.FutureDevice;
import com.springpageable.model.User;
import com.springpageable.model.mapper.FutureDeviceMapperImpl;
import com.springpageable.repository.FutureDeviceRepository;
import com.springpageable.repository.UserRepository;
import com.springpageable.service.FutureDeviceService;
import ie.corballis.fixtures.annotation.Fixture;
import ie.corballis.fixtures.annotation.FixtureAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.device.mock.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FutureDeviceServiceTest {

  private FutureDeviceService underTest;

  @Mock private FutureDeviceRepository mockFutureDeviceRepository;
  @Mock private UserRepository mockUserRepository;
  @Mock private Pageable mockPageable;

  @Fixture(FIXTURE_FUTURE_DEVICE_DTO)
  private FutureDeviceDTO futureDeviceDTO;

  @Fixture(FIXTURE_FUTURE_DEVICE)
  private List<FutureDevice> futureDeviceList;

  @Fixture(LIST_OF_USERS)
  private List<User> users;

  @Fixture(FIXTURE_GET_FUTURE_DEVICE_RESPONSE_DTO)
  private List<GetFutureDeviceResponseDTO> getFutureDeviceResponseDtoList;

  @BeforeEach
  void setUp() throws Exception {
    FixtureAnnotations.initFixtures(this);

    underTest =
        new FutureDeviceService(
            mockFutureDeviceRepository, new FutureDeviceMapperImpl(), mockUserRepository);
  }

  @Test
  void testThat_deleteFutureDevice_returnsResult() throws BadRequestException {
    // Arrange
    when(mockFutureDeviceRepository.findById(anyLong()))
        .thenReturn(Optional.of(futureDeviceList.get(0)));

    // Act
    underTest.deleteFutureDevice(TEST_CUSTOMER_ID);

    // Assert
    verify(mockFutureDeviceRepository).deleteById(anyLong());
  }

  @Test
  void testThat_delete_throwsResourceNotFound_inCaseIdDoesNotExists() {
    // Arrange
    when(mockFutureDeviceRepository.findById(anyLong())).thenReturn(Optional.empty());

    // Act
    var thrown =
        assertThrows(
            ResourceNotFoundException.class, () -> underTest.deleteFutureDevice(TEST_CUSTOMER_ID));

    // Assert
    assertEquals("No future device found for id: 15", thrown.getMessage());
  }

  @Test
  void testThat_createFutureDevice_throwsResourceNotFoundException_whenCustomer_notExists() {
    // Arrange
    when(mockUserRepository.findById(anyLong())).thenReturn(Optional.empty());

    // Act
    var thrown =
        assertThrows(
            ResourceNotFoundException.class, () -> underTest.createFutureDevice(futureDeviceDTO));

    // Assert
    assertEquals(
        String.format("There is no customer with id %d", futureDeviceDTO.getCustomerId()),
        thrown.getMessage());
  }

  @Test
  void
      testThat_createFutureDevice_throwsConflictException_whenCombinationAlreadyExistsInTheDatabase() {
    // Arrange
    when(mockUserRepository.findById(anyLong())).thenReturn(Optional.of(users.get(0)));
    when(mockFutureDeviceRepository.save(any(FutureDevice.class)))
        .thenThrow(new DataIntegrityViolationException("test"));

    // Act
    var thrown =
        assertThrows(ConflictException.class, () -> underTest.createFutureDevice(futureDeviceDTO));

    // Assert
    assertNotNull(thrown);
    assertEquals(
        String.format(
            "Combination with serial number %s,productId %s and customerId %d already exists",
            futureDeviceDTO.getSerialNumber(),
            futureDeviceDTO.getProductId(),
            futureDeviceDTO.getCustomerId()),
        thrown.getMessage());
  }

  @Test
  void testThat_retrieveFutureDevices_returnsResult() throws BadRequestException {
    // Arrange
    when(mockFutureDeviceRepository.findFutureDevices(any(Pageable.class), anyString()))
        .thenReturn(new PageImpl<>(futureDeviceList));
    // Act
    var actualResult =
        underTest.retrieveFutureDevices(mockPageable, TEST_SEARCH_PARAMETER).getContent();

    // Assert
    verify(mockFutureDeviceRepository).findFutureDevices(mockPageable, SEARCH_PARAMETER_KEY);
    assertEquals(getFutureDeviceResponseDtoList.get(0).getId(), actualResult.get(0).getId());
  }

  @Test
  void testThat_retrieveFutureDevices_whenNoResultFound() throws BadRequestException {
    // Arrange
    when(mockFutureDeviceRepository.findFutureDevices(any(Pageable.class), anyString()))
            .thenReturn(new PageImpl<>(List.of()));

    //Act
    var actualResult = underTest.retrieveFutureDevices(mockPageable, SEARCH_PARAMETER_KEY).getContent();

    // Assert
    assertNotNull(actualResult);
    assertTrue(actualResult.isEmpty());
  }
}
