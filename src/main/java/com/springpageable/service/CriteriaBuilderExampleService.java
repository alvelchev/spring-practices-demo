package com.springpageable.service;

import com.springpageable.dto.GetUserResponseDTO;
import com.springpageable.enums.LdapGroup;
import com.springpageable.model.User;
import com.springpageable.model.mapper.UserMapper;
import com.springpageable.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriteriaBuilderExampleService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Autowired
    public CriteriaBuilderExampleService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Find all users by username
     *
     * @param usernames        - List of usernames for filtering
     * @param excludeUsernames - usernames which to be excluded from the result set
     * @param searchParameter  - term to search by for users username, first name or last name
     * @param p                - pagination object containing page size, page number and sort parameters
     * @return Pageable result of users
     */
    public Page<GetUserResponseDTO> getUsers(List<String> usernames, List<String> excludeUsernames, List<LdapGroup> ldapGroups,
            String searchParameter, Pageable p) {
        Page<User> users = userRepository.getUsers(usernames, excludeUsernames, ldapGroups, searchParameter, p);

        List<GetUserResponseDTO> result = userMapper.listOfUserToListOfGetUserResponseDTO(users.getContent());

        return new PageImpl<>(result, p, users.getTotalElements());
    }
}
