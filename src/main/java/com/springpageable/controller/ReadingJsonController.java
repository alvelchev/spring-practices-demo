package com.springpageable.controller;

import com.springpageable.dto.CountryDTO;
import com.springpageable.service.ReadingJsonService;
import com.springpageable.swagger.SwaggerErrorResponses;
import com.springpageable.swagger.SwaggerGetCountryResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

import static com.springpageable.configuration.WebPath.*;

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
  @ApiResponse(responseCode = "200", content = @Content(
          array = @ArraySchema(schema = @Schema(implementation = SwaggerGetCountryResponseDTO.class))))
  public Set<CountryDTO> getCountriesByRegionType(
      @NotNull @Positive @PathVariable @Max(2) Integer regionId) {
    return readingJsonService.getCountriesByRegionType(regionId);
  }

  @GetMapping(PATH_COUNTRIES)
  @SwaggerErrorResponses
  @Operation(summary = "Retrieves countries by name")
  @Parameter(name = "name", example = "bul")
  @ApiResponse(responseCode = "200", content = @Content(
          array = @ArraySchema(schema = @Schema(implementation = SwaggerGetCountryResponseDTO.class))))
  public Set<CountryDTO> getCountries(@RequestParam(required = false) String name) {
    return readingJsonService.getCountries(name);
  }

}
