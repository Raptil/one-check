package org.startup.onecheck.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.startup.onecheck.model.dto.UserDto;
import org.startup.onecheck.model.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {


    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto (User user);

    User userDtoToUser (UserDto userDto);

    List<User> userDtoToUserList (List<UserDto> userDtos);

    List<UserDto> userToUserDtoList (List<User> users);
}
