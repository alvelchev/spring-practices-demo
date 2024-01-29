package com.springpracticesdemo.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springpracticesdemo.enums.LdapGroup;
import com.springpracticesdemo.model.User;

public interface UserRepositoryCriteria {

    /**
     * Returns a {@link Page} of {@link User} entities meeting the paging restriction provided in the {@code Pageable}
     * object. Retrieves users meeting different conditions, like search term (by username, email, lastname and
     * firstname), ldap group filtering or exclusion of usernames.
     *
     * @param usernames
     *            - optional List of usernames for filtering
     * @param excludeUsernames
     *            - optional List of usernames which to be excluded from the result set
     * @param ldapGroups
     *            - ldap groups for user retrieval
     * @param searchParameter
     *            - optional term to search by for users username, firstname, or lastname
     * @param p
     *            - a pageable component
     * @return a page of entities
     */
    Page<User> getUsers(List<String> usernames, List<String> excludeUsernames, List<LdapGroup> ldapGroups,
            String searchParameter,
            Pageable p);

}
