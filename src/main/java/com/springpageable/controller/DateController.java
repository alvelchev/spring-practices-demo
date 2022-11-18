package com.springpageable.controller;

import com.springpageable.dto.DateRequestDTO;
import com.springpageable.service.DateService;
import com.springpageable.swagger.SwaggerErrorResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DateController {

  private final DateService dateService;

  @Autowired
  public DateController(DateService dateService) {
    this.dateService = dateService;
  }

  @PostMapping(PATH_DATE)
  @SwaggerErrorResponses
  @ResponseStatus(HttpStatus.CREATED)
  @ApiResponse(responseCode = "201")
  public void dateValidator(@Valid @RequestBody DateRequestDTO dateRequestDto) {
    this.dateService.demo(dateRequestDto);
  }
}
