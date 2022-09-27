package com.device;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springpageable.controller.DeviceController;
import com.springpageable.dto.FutureDeviceDTO;
import com.springpageable.service.FutureDeviceService;
import ie.corballis.fixtures.annotation.Fixture;
import ie.corballis.fixtures.annotation.FixtureAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.springpageable.configuration.WebPath.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class FutureDeviceControllerTest {

  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @InjectMocks private DeviceController underTest;

  @Fixture("futureDeviceDTO")
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
            delete(API_VERSION_1 + PATH_REMOVE_FUTURE_DEVICE, 1L)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    // Assert
    verify(mockFutureDeviceService).deleteFutureDevice(anyLong());
  }
}
