package com.springpracticesdemo.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.springpracticesdemo.dto.GetUserResponseDTO;
import com.springpracticesdemo.enums.LdapGroup;
import com.springpracticesdemo.model.User;

@Mapper(componentModel = "spring", imports = { LdapGroup.class })
public interface UserMapper {

    List<GetUserResponseDTO> listOfUserToListOfGetUserResponseDTO(List<User> users);
}
