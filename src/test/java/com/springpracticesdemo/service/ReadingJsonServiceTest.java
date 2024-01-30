package com.springpracticesdemo.service;

import static com.springpracticesdemo.mock.Constants.TEST_CE_REGION_TYPE;
import static com.springpracticesdemo.mock.Constants.TEST_FRENCH_OVERSEAS_REGION_TYPE;
import static com.springpracticesdemo.mock.Constants.TEST_NON_SUPPORTED_REGION_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springpracticesdemo.dto.CountryDTO;
import com.springpracticesdemo.exception.BadRequestException;
import com.springpracticesdemo.storage.CountryStorage;

@ExtendWith(MockitoExtension.class)
class ReadingJsonServiceTest {

    @InjectMocks
    private ReadingJsonService underTest;

    @Mock
    private CountryStorage mockCountryStorage;

    private Set<CountryDTO> expectedResult;

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
        var exception = assertThrows(
                BadRequestException.class,
                () -> underTest.getCountriesByRegionType(TEST_NON_SUPPORTED_REGION_TYPE));
        assertNotNull(exception);
        assertEquals(
                String.format("Not supported region %d", TEST_NON_SUPPORTED_REGION_TYPE),
                exception.getMessage());
    }
}
