package com.springpracticesdemo.service;

import static com.springpracticesdemo.mock.Constants.FIXTURE_FUTURE_DEVICE;
import static com.springpracticesdemo.mock.Constants.FIXTURE_FUTURE_DEVICE_DTO;
import static com.springpracticesdemo.mock.Constants.FIXTURE_GET_FUTURE_DEVICE_RESPONSE_DTO;
import static com.springpracticesdemo.mock.Constants.LIST_OF_USERS;
import static com.springpracticesdemo.mock.Constants.SEARCH_PARAMETER_KEY;
import static com.springpracticesdemo.mock.Constants.TEST_CUSTOMER_ID;
import static com.springpracticesdemo.mock.Constants.TEST_SEARCH_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.springpracticesdemo.dto.FutureDeviceDTO;
import com.springpracticesdemo.dto.GetFutureDeviceResponseDTO;
import com.springpracticesdemo.event.ProcessEventPublisher;
import com.springpracticesdemo.exception.BadRequestException;
import com.springpracticesdemo.exception.ConflictException;
import com.springpracticesdemo.exception.ResourceNotFoundException;
import com.springpracticesdemo.model.FutureDevice;
import com.springpracticesdemo.model.User;
import com.springpracticesdemo.model.mapper.FutureDeviceMapperImpl;
import com.springpracticesdemo.repository.FutureDeviceRepository;
import com.springpracticesdemo.repository.UserRepository;

import ie.corballis.fixtures.annotation.Fixture;
import ie.corballis.fixtures.annotation.FixtureAnnotations;

@ExtendWith(MockitoExtension.class)
class FutureDeviceServiceTest {

    private FutureDeviceService underTest;

    @Mock
    private FutureDeviceRepository mockFutureDeviceRepository;
    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private ProcessEventPublisher mockApplicationEventPublisher;

    @Mock
    private Pageable mockPageable;

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

        underTest = new FutureDeviceService(
                mockFutureDeviceRepository, new FutureDeviceMapperImpl(), mockUserRepository,
                mockApplicationEventPublisher);
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
    void testThat_createFutureDevice_passed_successfully() {
        // Arrange
        when(mockUserRepository.findById(anyLong())).thenReturn(Optional.of(users.get(0)));
        when(mockFutureDeviceRepository.save(any(FutureDevice.class)))
                .thenReturn(futureDeviceList.get(0));

        // Act
        underTest.createFutureDevice(futureDeviceDTO);

        // Assert
        verify(mockFutureDeviceRepository).save(any());
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
