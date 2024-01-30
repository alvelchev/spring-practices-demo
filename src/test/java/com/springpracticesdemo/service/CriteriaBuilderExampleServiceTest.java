package com.springpracticesdemo.service;

import static com.springpracticesdemo.mock.Constants.LIST_OF_GET_USER_RESPONSE_DTO;
import static com.springpracticesdemo.mock.Constants.LIST_OF_USERS;
import static com.springpracticesdemo.mock.Constants.TEST_422_USERNAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.springpracticesdemo.dto.GetUserResponseDTO;
import com.springpracticesdemo.enums.LdapGroup;
import com.springpracticesdemo.model.User;
import com.springpracticesdemo.model.mapper.UserMapperImpl;
import com.springpracticesdemo.repository.UserRepository;

import ie.corballis.fixtures.annotation.Fixture;
import ie.corballis.fixtures.annotation.FixtureAnnotations;

@ExtendWith(MockitoExtension.class)
class CriteriaBuilderExampleServiceTest {

    private CriteriaBuilderExampleService underTest;

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private Pageable mockPageable;
    @Mock
    private User mockUser;

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
    verify(mockUserRepository, never()).findAll();
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
        var actualResult = underTest.getUsers(
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
