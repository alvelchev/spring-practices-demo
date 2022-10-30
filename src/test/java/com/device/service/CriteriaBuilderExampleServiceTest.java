package com.device.service;

import com.springpageable.dto.GetUserResponseDTO;
import com.springpageable.enums.LdapGroup;
import com.springpageable.model.User;
import com.springpageable.model.mapper.UserMapperImpl;
import com.springpageable.repository.UserRepository;
import com.springpageable.service.CriteriaBuilderExampleService;
import ie.corballis.fixtures.annotation.Fixture;
import ie.corballis.fixtures.annotation.FixtureAnnotations;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.device.mock.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriteriaBuilderExampleServiceTest {

  private CriteriaBuilderExampleService underTest;

  @Mock private UserRepository mockUserRepository;
  @Mock private Pageable mockPageable;
  @Mock private User mockUser;

  @Fixture(LIST_OF_USERS)
  private List<User> users;

  @Fixture(LIST_OF_GET_USER_RESPONSE_DTO)
  private List<GetUserResponseDTO> expectedResult;

  @BeforeEach
  void setUp() throws Exception {
    FixtureAnnotations.initFixtures(this);

    underTest = new CriteriaBuilderExampleService(mockUserRepository, new UserMapperImpl());
  }

  @Test
  void testThat_getUsersByListOfExcludeUsernames_returnsTheExpectedResult() {
    // Arrange
    when(mockUserRepository.getUsers(
            anyList(), anyList(), anyList(), anyString(), any(Pageable.class)))
        .thenReturn(new PageImpl<>(users));

    // Act
    var actualResult =
        underTest.getUsers(
            List.of(), List.of(TEST_422_USERNAME), List.of(), Strings.EMPTY, mockPageable);

    // Assert
    verify(mockUserRepository)
        .getUsers(List.of(), List.of(TEST_422_USERNAME), List.of(), Strings.EMPTY, mockPageable);
    verify(mockUserRepository, never()).findAll(any(Pageable.class));
    assertEquals(expectedResult, actualResult.getContent());
  }

  @Test
  void testThat_getUsersByListOfExcludeUsernamesAndProvidedLdapGroups_returnsTheExpectedResult() {
    // Arrange
    var ldapGroupsMock = List.of(LdapGroup.GENERAL);
    when(mockUserRepository.getUsers(
            anyList(), anyList(), anyList(), anyString(), any(Pageable.class)))
        .thenReturn(new PageImpl<>(users));

    // Act
    var actualResult =
        underTest.getUsers(
            List.of(), List.of(TEST_422_USERNAME), ldapGroupsMock, Strings.EMPTY, mockPageable);

    // Assert
    verify(mockUserRepository)
        .getUsers(
            List.of(), List.of(TEST_422_USERNAME), ldapGroupsMock, Strings.EMPTY, mockPageable);
    assertEquals(expectedResult, actualResult.getContent());
  }

  @Test
  void testThat_getUsersByListOfExcludeUsernamesAndSearchParameter_returnsTheExpectedResult() {
    // Arrange
    when(mockUserRepository.getUsers(
            anyList(), anyList(), anyList(), anyString(), any(Pageable.class)))
        .thenReturn(new PageImpl<>(users));

    // Act
    var actualResult =
        underTest.getUsers(
            List.of(), List.of(TEST_422_USERNAME), List.of(), TEST_422_USERNAME, mockPageable);

    // Assert
    verify(mockUserRepository)
        .getUsers(
            List.of(), List.of(TEST_422_USERNAME), List.of(), TEST_422_USERNAME, mockPageable);
    assertEquals(expectedResult, actualResult.getContent());
  }
}
