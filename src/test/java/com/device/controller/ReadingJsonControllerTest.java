package com.device.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springpageable.configuration.WebPath;
import com.springpageable.controller.ReadingJsonController;
import com.springpageable.dto.CountryDTO;
import com.springpageable.service.ReadingJsonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static com.device.mock.Constants.TEST_CE_REGION_TYPE;
import static com.device.mock.Constants.TEST_PARAM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ReadingJsonControllerTest {

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  Set<CountryDTO> expectedResult;

  @InjectMocks private ReadingJsonController underTest;

  @Mock private ReadingJsonService mockReadingJsonService;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    objectMapper = new ObjectMapper();
    expectedResult = Set.of(new CountryDTO());
  }

  @Test
  void testThat_getCountries_returnsResult() throws Exception {
    // Arrange
    when(mockReadingJsonService.getCountries(anyString())).thenReturn(expectedResult);

    // Act
    var mvcResult =
        mockMvc
            .perform(
                get(WebPath.API_VERSION_1 + WebPath.PATH_COUNTRIES)
                    .param("name", TEST_PARAM)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    var actualResult =
        objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(), new TypeReference<Set<CountryDTO>>() {});

    // Assert
    assertEquals(expectedResult, actualResult);
  }

  @Test
  void testThat_getCountriesInFrenchOverseasRegion_returnsResult() throws Exception {
    // Arrange
    when(mockReadingJsonService.getCountriesByRegionType(anyInt())).thenReturn(expectedResult);
    // Act
    var mvcResult =
        mockMvc
            .perform(
                get(WebPath.API_VERSION_1 + WebPath.PATH_COUNTRIES_REGION_TYPE, TEST_CE_REGION_TYPE)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    var actualResult =
        objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(), new TypeReference<Set<CountryDTO>>() {});
    // Assert
    assertEquals(expectedResult, actualResult);
  }
}
