package com.springpracticesdemo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpracticesdemo.dto.CountryDTO;
import com.springpracticesdemo.enums.RegionType;
import com.springpracticesdemo.exception.BadRequestException;
import com.springpracticesdemo.storage.CountryStorage;

@Service
public class ReadingJsonService {

    private final CountryStorage countryStorage;

    @Autowired
    public ReadingJsonService(CountryStorage countryStorage) {
        this.countryStorage = countryStorage;
    }

    public Set<CountryDTO> getCountries(String namePrefix) {
        return countryStorage.getCountriesByCountryNameStartsWith(namePrefix);
    }

    /**
     * Returns set of countries for requested region
     *
     * @param requestedRegion
     *            -the type of the requested region
     * @return {@link Set <CountryDTO>}
     */
    public Set<CountryDTO> getCountriesByRegionType(Integer requestedRegion) {
        switch (RegionType.getEnum(requestedRegion)) {
        case COUNTRIES_CE:
            return countryStorage.getCountriesInCERegion();
        case COUNTRIES_FRENCH_OVERSEAS:
            return countryStorage.getCountriesInFrenchOverseasRegion();
        default:
            throw new BadRequestException(String.format("Not supported region %s", requestedRegion));
        }
    }
}
