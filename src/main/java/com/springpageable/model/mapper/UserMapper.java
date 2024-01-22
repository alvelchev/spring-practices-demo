package com.springpageable.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.springpageable.dto.GetUserResponseDTO;
import com.springpageable.enums.LdapGroup;
import com.springpageable.model.User;

@Mapper(componentModel = "spring", imports = { LdapGroup.class })
public interface UserMapper {

    List<GetUserResponseDTO> listOfUserToListOfGetUserResponseDTO(List<User> users);
}
