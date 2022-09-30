package com.springpageable.repository.custom;

import com.springpageable.enums.LdapGroup;
import com.springpageable.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

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
                (totalRows == 0) ? List.of() : getResultList(usernames, excludeUsernames, groups, searchParameter, p), p,
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
                            criteriaBuilder.concat(root.get(FIRST_NAME), " "), root.get(LAST_NAME))), searchParameter)));
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
