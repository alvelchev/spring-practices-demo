package com.springpageable.controller;

import com.springpageable.dto.DateRequestDTO;
import com.springpageable.swagger.SwaggerErrorResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.springpageable.configuration.WebPath.API_VERSION_1;
import static com.springpageable.configuration.WebPath.PATH_DATE;

@RestController
@RequestMapping(API_VERSION_1)
@Validated
@Tag(name = "Date validator", description = "Show custom date validator in spring")
@Slf4j
public class DateController {


  @PostMapping(PATH_DATE)
  @SwaggerErrorResponses
  @ResponseStatus(HttpStatus.CREATED)
  @ApiResponse(responseCode = "201")
  @Operation(summary = "Creates new date")
  public void createFutureDevice(@RequestBody @Valid DateRequestDTO dateRequestDto) {
    log.info(String.valueOf(dateRequestDto));
  }

  }
