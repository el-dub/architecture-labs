package com.kpi.warehouse.controller.chain;

import com.kpi.warehouse.dto.user.UserCreateDto;
import com.kpi.warehouse.model.Role;

import java.util.Arrays;

public class RoleValidator extends BaseUserValidator {

    @Override
    public boolean validate(UserCreateDto userCreateDto) {
        if (Arrays.stream(Role.values()).noneMatch(role -> role.equals(userCreateDto.getRole()))) {
            return false;
        }
        return super.validate(userCreateDto);
    }
}
