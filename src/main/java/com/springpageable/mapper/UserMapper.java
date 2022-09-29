package com.springpageable.mapper;

import com.springpageable.dto.GetUserResponseDTO;
import com.springpageable.model.User;
import com.springpageable.utils.LdapGroup;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", imports = {LdapGroup.class})
public interface UserMapper {

    List<GetUserResponseDTO> listOfUserToListOfGetUserResponseDTO(List<User> users);
}
