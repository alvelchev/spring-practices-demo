package com.springpracticesdemo.repository.custom;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;

import com.springpracticesdemo.enums.LdapGroup;
import com.springpracticesdemo.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class UserRepositoryCriteriaImpl implements UserRepositoryCriteria {

    private static final String USERNAME = "username";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String LDAP_GROUPS = "ldapGroups";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<User> getUsers(List<String> usernames, List<String> excludeUsernames,
            List<LdapGroup> groups, String searchParameter, Pageable p) {
        final long totalRows = countAll(usernames, excludeUsernames, groups, searchParameter);
        return new PageImpl<>(
                (totalRows == 0) ? List.of() : getResultList(usernames, excludeUsernames, groups, searchParameter, p),
                p,
                totalRows);
    }

    private long countAll(List<String> usernames, List<String> excludeUsernames,
            List<LdapGroup> groups, String searchParameter) {
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<User> root = countQuery.from(User.class);
        countQuery.select(criteriaBuilder.countDistinct(root));
        applyQueryPredicates(criteriaBuilder, countQuery, root, usernames, excludeUsernames, groups, searchParameter);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private List<User> getResultList(List<String> usernames, List<String> excludeUsernames, List<LdapGroup> groups,
            String searchParameter, Pageable p) {
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<User> selectQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = selectQuery.from(User.class);
        selectQuery.select(root).distinct(true);
        applyQueryPredicates(criteriaBuilder, selectQuery, root, usernames, excludeUsernames, groups, searchParameter);
        selectQuery.orderBy(QueryUtils.toOrders(p.getSort(), root, criteriaBuilder));

        TypedQuery<User> typedQuery = entityManager.createQuery(selectQuery);
        typedQuery.setFirstResult(p.getPageNumber() * p.getPageSize());
        typedQuery.setMaxResults(p.getPageSize());
        return typedQuery.getResultList();
    }

    private void applyQueryPredicates(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> query, Root<User> root,
            List<String> usernames, List<String> excludeUsernames, List<LdapGroup> groups, String searchParameter) {
        final List<Predicate> predicates = new ArrayList<>(2);
        if (isNotEmpty(searchParameter)) {
            searchParameter = searchParameter.toLowerCase().trim() + "%";
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get(USERNAME)), searchParameter),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get(FIRST_NAME)), searchParameter),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get(LAST_NAME)), searchParameter),
                    criteriaBuilder.like(criteriaBuilder.lower(criteriaBuilder.concat(
                            criteriaBuilder.concat(root.get(FIRST_NAME), " "), root.get(LAST_NAME))),
                            searchParameter)));
        }
        if (!excludeUsernames.isEmpty()) {
            predicates.add(criteriaBuilder.not(root.get(USERNAME).in(excludeUsernames)));
        }
        if (!usernames.isEmpty()) {
            predicates.add(root.get(USERNAME).in(usernames));
        }
        if (!groups.isEmpty()) {
            predicates.add(root.join(LDAP_GROUPS).in(groups));
        }
        query.where(predicates.toArray(new Predicate[predicates.size()]));
    }

    private CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }
}
