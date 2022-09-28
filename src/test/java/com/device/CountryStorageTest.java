package com.device;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springpageable.storage.CountryStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import static com.device.mock.Constants.*;
import static com.springpageable.storage.CountryStorage.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryStorageTest {

    private static CountryStorage countryStorage;

    @BeforeAll
    static void beforeAll() {
        countryStorage = new CountryStorage();
    }

    @Test
    void testThat_countriesAndCERegionCountriesInitialization_throwsIOException() throws Exception {
        // Arrange
        var mapper = CountryStorage.class.getDeclaredField("mapper");
        mapper.setAccessible(true);

        // Mock object mapper to simulate IOException
        var mockMapper = Mockito.mock(ObjectMapper.class);

        when(mockMapper.readValue(eq(CountryStorage.class.getResource(COUNTRIES_RELATIVE_PATH)),
                any(TypeReference.class))).thenThrow(new IOException());

        when(mockMapper.readValue(eq(CountryStorage.class.getResource(COUNTRIES_CE_RELATIVE_PATH)),
                any(TypeReference.class))).thenThrow(new IOException());

        when(mockMapper.readValue(eq(CountryStorage.class.getResource(COUNTRIES_FRENCH_OVERSEAS_RELATIVE_PATH)),
                any(TypeReference.class))).thenThrow(new IOException());

        var modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(mapper, mapper.getModifiers() & ~Modifier.FINAL);

        // Set mocked object mapper to the testing class
        mapper.set(null, mockMapper);

        // Act
        var utils = new CountryStorage();

        // Assert
        assertEquals(0, utils.getCountriesByCountryNameStartsWith(null).size());
        assertEquals(0, utils.getCountriesInCERegion().size());
        assertEquals(0, utils.getCountriesInFrenchOverseasRegion().size());

        // Set real object mapper for other tests
        mapper.set(null, new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS));
    }

    @Test
    void testThat_getCountriesByCountryCodes_returnsTheExpectedCountries() {
        // Act
        var result = countryStorage.getCountriesByCountryCodes(List.of(COUNTRY_BG_KEY, COUNTRY_DE_KEY));

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals(2, result.stream()
                .filter(country -> country.getName().equals(COUNTRY_BG_VALUE) || country.getName().equals(COUNTRY_DE_VALUE))
                .count());
    }

    @Test
    void testThat_getCountriesByCountryCodes_returnsAllCountries_whenTheFilterIsEmpty() {
        // Act
        var result = countryStorage.getCountriesByCountryCodes(List.of());

        // Assert
        assertEquals(ALL_COUNTRIES_SIZE, result.size());
    }

    @Test
    void testThat_getCountriesByCountryNameStartsWith_returnsTheExpectedCountries() {
        // Act
        var result = countryStorage.getCountriesByCountryNameStartsWith(COUNTRY_BG_VALUE);

        // Assert
        assertFalse(result.isEmpty());
        assertTrue(result.stream().anyMatch(country -> country.getName().equals(COUNTRY_BG_VALUE)));
    }

    @Test
    void testThat_getCountriesByCountryNameStartsWith_returnsAllCountries_whenTheFilterIsNull() {
        // Act
        var result = countryStorage.getCountriesByCountryNameStartsWith(null);

        // Assert
        assertEquals(ALL_COUNTRIES_SIZE, result.size());
    }

    @Test
    void testThat_getCountryByCountryCode_returnsCountry_whenTheFilterIsValid() {
        // Act
        var result = countryStorage.getCountryByCountryCode(COUNTRY_BG_KEY);

        // Assert
        assertNotNull(result);
        assertEquals(COUNTRY_BG_VALUE, result.getName());
    }

    @Test
    void testThat_getCountryByCountryCode_returnsNull_whenTheFilterIsNull() {
        // Act
        var result = countryStorage.getCountryByCountryCode(null);

        // Assert
        assertNull(result);
    }

    @Test
    void testThat_getCountryByCountryCode_returnsNull_whenTheCountryDoesNotExist() {
        // Act
        var result = countryStorage.getCountryByCountryCode(NON_EXISTING_COUNTRY_CODE);

        // Assert
        assertNull(result);
    }

    @Test
    void testThat_areAllCountryCodesSupported_validatesProperly() {
        // Act & Assert
        assertTrue(countryStorage.areAllCountryCodesSupported(List.of(COUNTRY_BG_KEY, COUNTRY_DE_KEY)));
        assertFalse(
                countryStorage.areAllCountryCodesSupported(List.of(COUNTRY_BG_KEY, NON_EXISTING_COUNTRY_CODE, COUNTRY_DE_KEY)));
    }

    @Test
    void testThat_getCountriesByCountryNameStartsWith_returnsAllCountries_whenTheFilterIsEmpty() {
        // Act
        var result = countryStorage.getCountriesByCountryNameStartsWith(EMPTY_STRING);

        // Assert
        assertEquals(ALL_COUNTRIES_SIZE, result.size());
    }

    @Test
    void testThat_getCountriesInCERegion_returnsCountries() {
        // Act
        var result = countryStorage.getCountriesInCERegion();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testThat_getCountriesInFrenchOverseasRegion_returnsCountries() {
        // Act
        var result = countryStorage.getCountriesInFrenchOverseasRegion();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
