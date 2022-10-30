package com.device.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springpageable.advice.GlobalExceptionHandler;
import com.springpageable.controller.DateController;
import com.springpageable.dto.DateRequestDTO;
import com.springpageable.service.DateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static com.springpageable.configuration.WebPath.API_VERSION_1;
import static com.springpageable.configuration.WebPath.PATH_DATE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DateControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @InjectMocks
    private DateController underTest;

    @Mock
    private DateService mockDateService;


    @BeforeEach
    void setUp() throws Exception {
        mockMvc =
                MockMvcBuilders.standaloneSetup(underTest)
                        .setControllerAdvice(new GlobalExceptionHandler())
                        .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                        .build();

        objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .registerModule(new JavaTimeModule());
    }

    @Test
    void testThat_dateController_invokesDateService() throws Exception {
        DateRequestDTO dateRequestDTO =
                new DateRequestDTO(LocalDate.now(), LocalDate.now().plusDays(1));
        // Act
        mockMvc
                .perform(
                        post(API_VERSION_1 + PATH_DATE)
                                .content(objectMapper.writeValueAsString(dateRequestDTO))
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(mockDateService).demo(any(DateRequestDTO.class));
    }
}