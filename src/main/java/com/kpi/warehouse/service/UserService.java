package com.kpi.warehouse.service;

import com.kpi.warehouse.dto.user.UserCreateDto;
import com.kpi.warehouse.dto.user.UserDto;
import com.kpi.warehouse.model.User;

public interface UserService {

    UserDto addUser(UserCreateDto userDto);

    User getUserById(Long userId);
}
