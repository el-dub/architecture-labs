package com.kpi.warehouse.service.impl;

import com.kpi.warehouse.dto.mapper.UserMapper;
import com.kpi.warehouse.dto.user.UserCreateDto;
import com.kpi.warehouse.dto.user.UserDto;
import com.kpi.warehouse.exception.NotFoundException;
import com.kpi.warehouse.model.User;
import com.kpi.warehouse.repository.UserRepository;
import com.kpi.warehouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto addUser(UserCreateDto userDto) {
        User user = userMapper.fromDto(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with ID %s not found", userId)));
    }
}
