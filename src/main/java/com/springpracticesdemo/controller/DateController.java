package com.springpracticesdemo.controller;

import static com.springpracticesdemo.configuration.WebPath.API_VERSION_1;
import static com.springpracticesdemo.configuration.WebPath.PATH_DATE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springpracticesdemo.dto.DateRequestDTO;
import com.springpracticesdemo.service.DateService;
import com.springpracticesdemo.swagger.SwaggerErrorResponses;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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
