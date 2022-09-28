package com.springpageable.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springpageable.dto.CountryDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
public class CountryStorage {
  private final SortedSet<CountryDTO> countries;
  private final SortedSet<CountryDTO> ceRegionCountries;
  private final SortedSet<CountryDTO> frenchOverseasCountries;
  public static final String COUNTRIES_RELATIVE_PATH = "/static/countries.json";
  public static final String COUNTRIES_CE_RELATIVE_PATH = "/static/countriesCE.json";
  public static final String COUNTRIES_FRENCH_OVERSEAS_RELATIVE_PATH =
      "/static/countriesFrenchOverseas.json";

  private static final ObjectMapper mapper =
      new ObjectMapper()
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

  public CountryStorage() {
    TreeSet<CountryDTO> countryDTOS;

    try {
      countryDTOS =
          mapper.readValue(
              CountryStorage.class.getResource(COUNTRIES_RELATIVE_PATH), new TypeReference<>() {});
    } catch (IOException e) {
      log.error("Failed to load countries. {}", e.getMessage(), e);
      countryDTOS = new TreeSet<>();
    }
    countries = Collections.unmodifiableSortedSet(countryDTOS);

    try {
      countryDTOS =
          mapper.readValue(
              CountryStorage.class.getResource(COUNTRIES_CE_RELATIVE_PATH),
              new TypeReference<>() {});

    } catch (IOException e) {
      log.error("Failed to load CE region countries. {}", e.getMessage(), e);
      countryDTOS = new TreeSet<>();
    }

    ceRegionCountries = Collections.unmodifiableSortedSet(countryDTOS);

    try {
      countryDTOS =
          mapper.readValue(
              CountryStorage.class.getResource(COUNTRIES_FRENCH_OVERSEAS_RELATIVE_PATH),
              new TypeReference<>() {});

    } catch (IOException e) {
      log.error("Failed to load French overseas region countries. {}", e.getMessage(), e);
      countryDTOS = new TreeSet<>();
    }

    frenchOverseasCountries = Collections.unmodifiableSortedSet(countryDTOS);
  }

  /**
   * Filters the countries by list of country codes. In case all countries are retrieved (no filter
   * is passed), unmodifiable set is returned. Filtered countries are sorted by country name.
   *
   * @param countryCodes - used for country filtration
   * @return sorted set of {@link CountryDTO} which contains the country code and country name
   */
  public Set<CountryDTO> getCountriesByCountryCodes(List<String> countryCodes) {
    return isEmpty(countryCodes)
        ? countries
        : countries.stream()
            .filter(
                country ->
                    countryCodes.stream()
                        .anyMatch(
                            countryCode -> countryCode.equalsIgnoreCase(country.getCountryCode())))
            .collect(Collectors.toCollection(TreeSet::new));
  }

  /**
   * Retrieves the single country object for given country code
   *
   * @param countryCode - parameter for filtering country object
   * @return {@link CountryDTO}
   */
  public CountryDTO getCountryByCountryCode(String countryCode) {
    if (StringUtils.isBlank(countryCode)) {
      return null;
    }

    return countries.stream()
        .filter(country -> countryCode.equals(country.getCountryCode()))
        .findFirst()
        .orElse(null);
  }

  /**
   * Iterates over all countries and for each countryCode searches for exact match (case sensitive)
   *
   * @param countryCodes - list of countryCodes of the countries
   * @return - are all country codes supported
   */
  public boolean areAllCountryCodesSupported(List<String> countryCodes) {
    return countryCodes.stream()
        .noneMatch(
            countryCode ->
                countries.stream()
                    .noneMatch(country -> country.getCountryCode().equals(countryCode)));
  }

  /**
   * Filters the countries by name prefix. In case all countries are retrieved (no filter is
   * passed), unmodifiable set is returned. Filtered countries are sorted by country namePrefix.
   *
   * @param namePrefix - country name prefix
   * @return sorted set of {@link CountryDTO} which contains the country code and country name
   */
  public Set<CountryDTO> getCountriesByCountryNameStartsWith(String namePrefix) {
    return StringUtils.isBlank(namePrefix)
        ? countries
        : countries.stream()
            .filter(country -> country.getName().toLowerCase().startsWith(namePrefix.toLowerCase()))
            .collect(Collectors.toCollection(TreeSet::new));
  }

  /**
   * Retrieves unmodifiable set of CE region countries.
   *
   * @return list of CE region countries which contains country name and countryCode
   */
  public Set<CountryDTO> getCountriesInCERegion() {
    return ceRegionCountries;
  }

  /**
   * Retrieves unmodifiable set of French overseas region countries.
   *
   * @return list of French overseas region countries which contains country name and countryCode
   */

  public Set<CountryDTO> getCountriesInFrenchOverseasRegion() {
    return frenchOverseasCountries;
  }
}
