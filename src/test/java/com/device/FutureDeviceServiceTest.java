package com.device;

import com.springpageable.dto.FutureDeviceDTO;
import com.springpageable.dto.GetFutureDeviceResponseDTO;
import com.springpageable.exception.BadRequestException;
import com.springpageable.exception.ResourceNotFoundException;
import com.springpageable.model.FutureDevice;
import com.springpageable.model.mapper.FutureDeviceMapperImpl;
import com.springpageable.repository.FutureDeviceRepository;
import com.springpageable.service.FutureDeviceService;
import ie.corballis.fixtures.annotation.Fixture;
import ie.corballis.fixtures.annotation.FixtureAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.device.mock.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FutureDeviceServiceTest {

  private FutureDeviceService underTest;

  @Mock private FutureDeviceRepository mockFutureDeviceRepository;
  @Mock private Pageable mockPageable;

  @Fixture(FIXTURE_FUTURE_DEVICE_DTO)
  private FutureDeviceDTO futureDeviceDTO;
  @Fixture(FIXTURE_FUTURE_DEVICE)
  private List<FutureDevice> futureDeviceList;

  @Fixture(FIXTURE_GET_FUTURE_DEVICE_RESPONSE_DTO)
  private List<GetFutureDeviceResponseDTO> getFutureDeviceResponseDtoList;

  @BeforeEach
  void setUp() throws Exception {
    FixtureAnnotations.initFixtures(this);

    underTest = new FutureDeviceService(mockFutureDeviceRepository, new FutureDeviceMapperImpl());
  }

  @Test
  void testThat_deleteFutureDevice_returnsResult() throws BadRequestException {
    // Arrange
    when(mockFutureDeviceRepository.findById(anyLong())).thenReturn(Optional.of(futureDeviceList.get(0)));

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
        assertThrows(ResourceNotFoundException.class, () -> underTest.deleteFutureDevice(TEST_CUSTOMER_ID));

    // Assert
    assertEquals("No future device found for id: 15", thrown.getMessage());
  }
}
