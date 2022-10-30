package com.device.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springpageable.advice.GlobalExceptionHandler;
import com.springpageable.configuration.WebPath;
import com.springpageable.controller.CriteriaBuilderExampleController;
import com.springpageable.dto.GetUserResponseDTO;
import com.springpageable.service.CriteriaBuilderExampleService;
import ie.corballis.fixtures.annotation.Fixture;
import ie.corballis.fixtures.annotation.FixtureAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.device.mock.Constants.LIST_OF_GET_USER_RESPONSE_DTO;
import static com.device.mock.Constants.TEST_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CriteriaBuilderExampleControllerTest {

  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @InjectMocks private CriteriaBuilderExampleController underTest;

  @Mock private CriteriaBuilderExampleService mockUserService;

  @Fixture(LIST_OF_GET_USER_RESPONSE_DTO)
  private List<GetUserResponseDTO> getUserResponseDTOs;

  @BeforeEach
  void setUp() throws Exception {
    FixtureAnnotations.initFixtures(this);

    mockMvc =
        MockMvcBuilders.standaloneSetup(underTest)
            .setControllerAdvice(new GlobalExceptionHandler())
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();

    objectMapper = new ObjectMapper();
  }

  @Test
  void testThat_getUsers_returnsResult() throws Exception {
    // Arrange
    when(mockUserService.getUsers(
            anyList(), or(isNull(), anyList()), anyList(), anyString(), any(Pageable.class)))
        .thenReturn(new PageImpl<>(getUserResponseDTOs));

    // Act
    var mvcResult =
        mockMvc
            .perform(get(WebPath.API_VERSION_1 + WebPath.PATH_USERS).contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    // Assert
    verify(mockUserService)
        .getUsers(anyList(), or(isNull(), anyList()), anyList(), anyString(), any(Pageable.class));
    var actualResult = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
    var actualContent =
        objectMapper.readValue(
            actualResult.get(TEST_CONTENT).toString(),
            new TypeReference<List<GetUserResponseDTO>>() {});

    assertEquals(getUserResponseDTOs, actualContent);
  }
}
