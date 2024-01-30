package com.springpracticesdemo.controller;

import static com.springpracticesdemo.configuration.WebPath.API_VERSION_1;
import static com.springpracticesdemo.configuration.WebPath.PATH_COUNTRIES;
import static com.springpracticesdemo.configuration.WebPath.PATH_COUNTRIES_REGION_TYPE;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springpracticesdemo.dto.CountryDTO;
import com.springpracticesdemo.service.ReadingJsonService;
import com.springpracticesdemo.swagger.SwaggerErrorResponses;
import com.springpracticesdemo.swagger.SwaggerGetCountryResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping(API_VERSION_1)
@Tag(name = "Country Operations", description = "Retrieving information about countries (resolving their ISO code and name)")
public class ReadingJsonController {

    private final ReadingJsonService readingJsonService;

    @Autowired
    public ReadingJsonController(ReadingJsonService readingJsonService) {
        this.readingJsonService = readingJsonService;
    }

    @GetMapping(PATH_COUNTRIES_REGION_TYPE)
    @SwaggerErrorResponses
    @Operation(summary = "Retrieves countries by regionType")
    @Parameter(name = "regionId", example = "1", description = "The value of RegionType enumeration")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SwaggerGetCountryResponseDTO.class))))
    public Set<CountryDTO> getCountriesByRegionType(
            @NotNull @Positive @PathVariable @Max(2) Integer regionId) {
        return readingJsonService.getCountriesByRegionType(regionId);
    }

    @GetMapping(PATH_COUNTRIES)
    @SwaggerErrorResponses
    @Operation(summary = "Retrieves countries by name")
    @Parameter(name = "name", example = "bul")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SwaggerGetCountryResponseDTO.class))))
    public Set<CountryDTO> getCountries(@RequestParam(required = false) String name) {
        return readingJsonService.getCountries(name);
    }

}
