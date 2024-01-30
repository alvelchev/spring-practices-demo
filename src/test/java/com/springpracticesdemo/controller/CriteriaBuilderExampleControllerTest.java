package com.springpracticesdemo.controller;

import static com.springpracticesdemo.mock.Constants.LIST_OF_GET_USER_RESPONSE_DTO;
import static com.springpracticesdemo.mock.Constants.TEST_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springpracticesdemo.advice.GlobalExceptionHandler;
import com.springpracticesdemo.configuration.WebPath;
import com.springpracticesdemo.dto.GetUserResponseDTO;
import com.springpracticesdemo.service.CriteriaBuilderExampleService;

import ie.corballis.fixtures.annotation.Fixture;
import ie.corballis.fixtures.annotation.FixtureAnnotations;

@ExtendWith(MockitoExtension.class)
class CriteriaBuilderExampleControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @InjectMocks
    private CriteriaBuilderExampleController underTest;

    @Mock
    private CriteriaBuilderExampleService mockUserService;

    @Fixture(LIST_OF_GET_USER_RESPONSE_DTO)
    private List<GetUserResponseDTO> getUserResponseDTOs;

    @BeforeEach
    void setUp() throws Exception {
        FixtureAnnotations.initFixtures(this);

        mockMvc = MockMvcBuilders.standaloneSetup(underTest)
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
        .thenReturn(new PageImpl<>(getUserResponseDTOs, PageRequest.of(0, 10), getUserResponseDTOs.size()));

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
