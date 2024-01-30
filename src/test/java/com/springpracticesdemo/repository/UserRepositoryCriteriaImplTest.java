package com.springpracticesdemo.repository;

import static com.springpracticesdemo.mock.Constants.TEST_422_USERNAME;
import static com.springpracticesdemo.mock.Constants.TEST_SEARCH_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;

import com.springpracticesdemo.enums.LdapGroup;
import com.springpracticesdemo.model.User;
import com.springpracticesdemo.repository.custom.UserRepositoryCriteria;
import com.springpracticesdemo.repository.custom.UserRepositoryCriteriaImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

@ExtendWith(MockitoExtension.class)
class UserRepositoryCriteriaImplTest {

    private UserRepositoryCriteria underTest;

    @Mock
    private EntityManager mockEntityManager;
    @Mock
    private Pageable mockPageable;
    @Mock
    private CriteriaBuilder mockCriteriaBuilder;
    @Mock
    private CriteriaQuery<Long> mockCountQuery;
    @Mock
    private CriteriaQuery<User> mockSelectQuery;
    @Mock
    private Root<User> mockRoot;
    @Mock
    private TypedQuery<User> mockTypedQuerySelect;
    @Mock
    private TypedQuery<Long> mockTypedQueryCount;
    @Mock
    private Path<Object> mockPath;

    private final long typedQueryTotalCountMock = 100;
    private final int PAGEABLE_CURRENT_PAGE = 0;
    private final int PAGEABLE_PAGE_SIZE = 5;

    @BeforeEach
    void setUp() {
        underTest = new UserRepositoryCriteriaImpl();

        ReflectionTestUtils.setField(underTest, "entityManager", mockEntityManager);

        when(mockEntityManager.getCriteriaBuilder()).thenReturn(mockCriteriaBuilder);
        when(mockEntityManager.createQuery(mockCountQuery)).thenReturn(mockTypedQueryCount);
        when(mockCriteriaBuilder.createQuery(Long.class)).thenReturn(mockCountQuery);
        when(mockCountQuery.from(User.class)).thenReturn(mockRoot);
        when(mockSelectQuery.select(any())).thenReturn(mockSelectQuery);
        when(mockTypedQueryCount.getSingleResult()).thenReturn(typedQueryTotalCountMock);
        when(mockRoot.get(anyString())).thenReturn(mockPath);
        when(mockPageable.getSort()).thenReturn(Sort.by(List.of()));
        when(mockPageable.getPageSize()).thenReturn(PAGEABLE_PAGE_SIZE);
        when(mockPageable.getPageNumber()).thenReturn(PAGEABLE_CURRENT_PAGE);
    }

  @Test
  void testThat_getUsers_withUsernames_returnsResult() {
    // Arrange
    when(mockEntityManager.createQuery(mockSelectQuery)).thenReturn(mockTypedQuerySelect);
    when(mockCriteriaBuilder.createQuery(User.class)).thenReturn(mockSelectQuery);
    when(mockSelectQuery.from(User.class)).thenReturn(mockRoot);
    var mockList = List.of(mock(User.class), mock(User.class));
    when(mockTypedQuerySelect.getResultList()).thenReturn(mockList);

    // Act
    var result =
        underTest.getUsers(
            List.of(TEST_422_USERNAME), List.of(), List.of(), TEST_SEARCH_PARAMETER, mockPageable);

    // Assert
    assertEquals(typedQueryTotalCountMock, result.getTotalElements());
    assertEquals(mockList, result.getContent());

    verify(mockCriteriaBuilder).createQuery(User.class);
    verify(mockEntityManager, times(2)).getCriteriaBuilder();
  }

  @Test
  void testThat_getUsers_withExcludeUsernames_returnsResult() {
    // Arrange
    when(mockEntityManager.createQuery(mockSelectQuery)).thenReturn(mockTypedQuerySelect);
    when(mockCriteriaBuilder.createQuery(User.class)).thenReturn(mockSelectQuery);
    when(mockSelectQuery.from(User.class)).thenReturn(mockRoot);
    var mockList = List.of(mock(User.class), mock(User.class));
    when(mockTypedQuerySelect.getResultList()).thenReturn(mockList);

    // Act
    var result =
        underTest.getUsers(
            List.of(), List.of(TEST_422_USERNAME), List.of(), TEST_SEARCH_PARAMETER, mockPageable);

    // Assert
    assertEquals(typedQueryTotalCountMock, result.getTotalElements());
    assertEquals(mockList, result.getContent());

    verify(mockCriteriaBuilder).createQuery(User.class);
    verify(mockEntityManager, times(2)).getCriteriaBuilder();
  }

    @Test
    void testThat_getUsers_withLdapGroups_returnsResult() {
        // Arrange
        @SuppressWarnings("unchecked")
        Join<Object, Object> mockJoin = mock(Join.class);
        when(mockEntityManager.createQuery(mockSelectQuery)).thenReturn(mockTypedQuerySelect);
        when(mockCriteriaBuilder.createQuery(User.class)).thenReturn(mockSelectQuery);
        when(mockSelectQuery.from(User.class)).thenReturn(mockRoot);
        when(mockRoot.join(anyString())).thenReturn(mockJoin);
        var mockList = List.of(mock(User.class), mock(User.class));
        when(mockTypedQuerySelect.getResultList()).thenReturn(mockList);

        // Act
        var result = underTest.getUsers(
                List.of(), List.of(TEST_422_USERNAME), List.of(LdapGroup.GENERAL), "", mockPageable);

        // Assert
        assertEquals(typedQueryTotalCountMock, result.getTotalElements());
        assertEquals(mockList, result.getContent());

        verify(mockCriteriaBuilder).createQuery(User.class);
        verify(mockEntityManager, times(2)).getCriteriaBuilder();
    }
}
