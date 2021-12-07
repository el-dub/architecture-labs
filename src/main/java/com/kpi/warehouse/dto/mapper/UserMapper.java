package com.kpi.warehouse.dto.mapper;

import com.kpi.warehouse.dto.user.UserCreateDto;
import com.kpi.warehouse.dto.user.UserDto;
import com.kpi.warehouse.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromDto(UserCreateDto userCreateDto);

    UserDto toDto(User user);
}
