package com.device.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springpageable.controller.DeviceController;
import com.springpageable.dto.FutureDeviceDTO;
import com.springpageable.dto.GetFutureDeviceResponseDTO;
import com.springpageable.service.FutureDeviceService;
import ie.corballis.fixtures.annotation.Fixture;
import ie.corballis.fixtures.annotation.FixtureAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.device.mock.Constants.*;
import static com.springpageable.configuration.WebPath.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class FutureDeviceControllerTest {

  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @InjectMocks private DeviceController underTest;

  @Fixture(FIXTURE_FUTURE_DEVICE_DTO)
  private FutureDeviceDTO futureDeviceDTO;

  @Mock private FutureDeviceService mockFutureDeviceService;

  @BeforeEach
  void setUp() throws Exception {
    FixtureAnnotations.initFixtures(this);

    mockMvc =
        MockMvcBuilders.standaloneSetup(underTest)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();

    objectMapper = new ObjectMapper();
  }

  @Test
  void testThat_retrieveFutureDevices_returnsResult() throws Exception {
    // Arrange
    var expectedResult = List.of(new GetFutureDeviceResponseDTO());
    when(mockFutureDeviceService.retrieveFutureDevices(any(Pageable.class), anyString()))
        .thenReturn(new PageImpl<>(expectedResult));

    // Act
    var mvcResult =
        mockMvc
            .perform(
                get(API_VERSION_1 + PATH_FUTURE_DEVICES)
                    .param(SEARCH_PARAMETER_KEY, TEST_SERIAL_NUMBER)
                    .contentType(APPLICATION_JSON)
                    .accept(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    var actualResult = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
    var actualContent =
        objectMapper.readValue(
            actualResult.get(CONTENT_KEY).toString(),
            new TypeReference<List<GetFutureDeviceResponseDTO>>() {});

    // Assert
    assertEquals(expectedResult, actualContent);
    verify(mockFutureDeviceService)
        .retrieveFutureDevices(any(Pageable.class), eq(TEST_SERIAL_NUMBER));
  }

  @Test
  void testThat_createDeviceFutureCatalog_invokesFutureDeviceService() throws Exception {
    // Act
    mockMvc
        .perform(
            post(API_VERSION_1 + PATH_FUTURE_DEVICES)
                .content(objectMapper.writeValueAsString(futureDeviceDTO))
                .contentType(APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  void testThat_deleteFutureDevice_returnsStatusOk() throws Exception {
    // Act
    mockMvc
        .perform(
            delete(API_VERSION_1 + PATH_REMOVE_FUTURE_DEVICE, TEST_CUSTOMER_ID)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    // Assert
    verify(mockFutureDeviceService).deleteFutureDevice(anyLong());
  }
}
