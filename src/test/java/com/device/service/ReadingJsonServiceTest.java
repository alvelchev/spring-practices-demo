package com.device.service;

import com.springpageable.dto.CountryDTO;
import com.springpageable.exception.BadRequestException;
import com.springpageable.service.ReadingJsonService;
import com.springpageable.storage.CountryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static com.device.mock.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadingJsonServiceTest {

  @InjectMocks private ReadingJsonService underTest;

  @Mock private CountryStorage mockCountryStorage;

  Set<CountryDTO> expectedResult;

  @BeforeEach
  void init() {
    expectedResult = Set.of(new CountryDTO());
  }

  @Test
  void testThat_getCountries_returnsResult() {
    // Arrange
    when(mockCountryStorage.getCountriesByCountryNameStartsWith(or(isNull(), anyString())))
        .thenReturn(expectedResult);

    // Act
    var actualResult = underTest.getCountries(null);

    // Assert
    assertNotNull(expectedResult);
    assertEquals(expectedResult, actualResult);
  }

  @Test
  void testThat_getCountriesInCeRegion_returnsResult() {
    // Arrange
    when(mockCountryStorage.getCountriesInCERegion()).thenReturn(expectedResult);

    // Act
    var actualResult = underTest.getCountriesByRegionType(TEST_CE_REGION_TYPE);

    // Assert
    assertNotNull(expectedResult);
    assertEquals(expectedResult, actualResult);
  }

  @Test
  void testThat_getCountriesInFrenchOverSeasRegion_returnsResult() {
    // Arrange
    var expectedResult = Set.of(new CountryDTO());
    when(mockCountryStorage.getCountriesInFrenchOverseasRegion()).thenReturn(expectedResult);

    // Act
    var actualResult = underTest.getCountriesByRegionType(TEST_FRENCH_OVERSEAS_REGION_TYPE);

    // Assert
    assertNotNull(expectedResult);
    assertEquals(expectedResult, actualResult);
  }

  @Test
  void testThat_getCountriesByRegionType_whenRegionIsNotSupported() {
    // Act & Assert
    var exception =
        assertThrows(
            BadRequestException.class,
            () -> underTest.getCountriesByRegionType(TEST_NON_SUPPORTED_REGION_TYPE));
    assertNotNull(exception);
    assertEquals(
        String.format("Not supported region %d", TEST_NON_SUPPORTED_REGION_TYPE),
        exception.getMessage());
  }
}
